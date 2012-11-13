package uk.ac.glam.smartwps.client.wps;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import uk.ac.glam.smartwps.client.SmartWPS;
import uk.ac.glam.smartwps.client.datatree.CoverageNode;
import uk.ac.glam.smartwps.client.net.WPSRequestService;
import uk.ac.glam.smartwps.client.net.WPSRequestServiceAsync;
import uk.ac.glam.smartwps.shared.request.WPSExecuteRequest;
import uk.ac.glam.smartwps.shared.response.WPSExecuteResponse;
import uk.ac.glam.smartwps.shared.wcs111.WCSCoverage;
import uk.ac.glam.smartwps.shared.wfs.WFSFeatureType;
import uk.ac.glam.smartwps.shared.wps.ComplexData;
import uk.ac.glam.smartwps.shared.wps.DetailedProcessDescriptor;
import uk.ac.glam.smartwps.shared.wps.Format;
import uk.ac.glam.smartwps.shared.wps.LiteralData;
import uk.ac.glam.smartwps.shared.wps.WPSData;
import uk.ac.glam.smartwps.shared.wps.input.LiteralProcessInput;
import uk.ac.glam.smartwps.shared.wps.input.WCSProcessInput;
import uk.ac.glam.smartwps.shared.wps.input.WFSProcessInput;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.AttachEvent;
import com.google.gwt.event.logical.shared.AttachEvent.Handler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Button;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.ComboBoxItem;
import com.smartgwt.client.widgets.form.fields.FormItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.fields.events.ChangedEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;
import java.util.List;
import uk.ac.glam.smartwps.shared.util.StringUtils;

/**
 * A SmartGWT window for running a WPS process. The window contents are generated from the given DetailedProcessDescriptor.
 * 
 * @author Jon Britton
 *
 */
public class RunProcessWindow extends Window {
	private DetailedProcessDescriptor process;
//	private WPSExecuteResponse result;
	private DynamicForm literalsForm;
	private static final Logger LOGGER = SmartWPS.LOGGER;
	private ProcessInputManager inputManager;
	private Map<FormItem, String> defaultValues = new HashMap<>();
	private static WPSRequestServiceAsync wpsService = GWT.create(WPSRequestService.class);
	
	/**
	 * Creates a new RunProcessWindow from the given DetailedProcessDescriptor.
	 * @param process The process to run
	 */
	public RunProcessWindow(DetailedProcessDescriptor process) {
		this.process = process;
		inputManager = new ProcessInputManager();
		
		setTitle("Run: " + process.getTitle());
		setWidth(600);
		setHeight(500);
		setShowMinimizeButton(false);
		setIsModal(true);
		setShowModalMask(true);
		centerInPage();
		
		final VLayout mainLayout = new VLayout(5);
		mainLayout.setLayoutMargin(5);
		mainLayout.setWidth100();
		mainLayout.setHeight100();
		
		final VLayout formsLayout = new VLayout();
		formsLayout.setWidth100();
		formsLayout.setHeight100();
		
		// in case we have literals...		
		List<FormItem> literalFormItems = new ArrayList<>();
		
		List<WPSData> inputs = process.getDataInputs();
        for (WPSData wpsData : inputs) {
			// Determine input data type and create necessary widgets
			if (wpsData instanceof ComplexData) {
				ComplexData complexData = (ComplexData) wpsData;
				if (complexData.supportsFormat(Format.IMAGE_TIFF)) {
					// Create form for this input
					InputLayout coverageInputLayout = new CoverageInputLayout(complexData);
					formsLayout.addMember(coverageInputLayout);
				} else if (complexData.supportsFormat(Format.TEXT_XML)) {
					// Probably wants some GML...				
					InputLayout featureInputLayout = new FeatureInputLayout(complexData);
					formsLayout.addMember(featureInputLayout);
				}
			} else if (wpsData instanceof LiteralData) {
				boolean optional = wpsData.getMinOccurs() == 0;
				if (literalsForm == null) {
					literalsForm = new DynamicForm();
					literalsForm.setGroupTitle("Literal Values");
					literalsForm.setIsGroup(true);
				}
				LiteralData literalData = (LiteralData) wpsData;
				FormItem literalItem;
				// AllowedValues
				List<String> allowedValues = literalData.getAllowedValues();
				if ((allowedValues != null) && (allowedValues.size() > 0)) {
					literalItem = new ComboBoxItem();
					literalItem.setType("comboBox");
					literalItem.setValueMap(allowedValues.toArray(new String[allowedValues.size()]));
				} else {
					literalItem = new TextItem();
				}
				// DefaultValue
				if ((literalData.getDefaultValue() != null) && literalData.getDefaultValue().length() != 0) {
					literalItem.setDefaultValue(literalData.getDefaultValue());
					// Keep track of default values
					defaultValues.put(literalItem, literalData.getDefaultValue());
				}
				literalItem.setName(wpsData.getIdentifier());
				literalItem.setTitle(optional ? (wpsData.getIdentifier() + "(optional)") : wpsData.getIdentifier());
				literalItem.setHint(wpsData.getTitle());
				literalFormItems.add(literalItem);
			}
		}
		
		// if we have literal values, add them to the form
		if (literalsForm != null) {
			literalsForm.setItems(literalFormItems.toArray(new FormItem[literalFormItems.size()]));
			formsLayout.addMember(literalsForm);
		}
						
		mainLayout.addMember(formsLayout);
		
		HLayout bottomLayout = new HLayout();
		bottomLayout.setAlign(Alignment.RIGHT);
		IButton runButton = new IButton("Run");
		runButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				runProcess();
			}
		});
		bottomLayout.addMember(runButton);
		
		mainLayout.addMember(bottomLayout);
		
		addItem(mainLayout);
	}
	
	/**
	 * Retrieve process inputs from forms and make the execute request.
	 */
	void runProcess() {
		WPSExecuteRequest request = new WPSExecuteRequest(process);
		LOGGER.log(Level.INFO, "Running process...");

		List<WPSData> inputs = process.getDataInputs();
        for (WPSData input : inputs) {
			
			if (input instanceof ComplexData) {
				ComplexData complexData = (ComplexData) input;
				// Could have more than one instance of each input...
				List<InputForm> inputForms = inputManager.getInputs(complexData.getIdentifier());
				if (complexData.getDefaultFormat().getMimeType().equals(Format.IMAGE_TIFF)) {
					// WCS
                    for (InputForm inputForm : inputForms) {
						String localName = inputForm.getInputValue();
						if (localName != null && localName.length() != 0) {
							WCSCoverage cov = SmartWPS.getSmartWPS().getDataTree().getWCSCoverageByLocalName(localName);
							WCSProcessInput wcsInput = new WCSProcessInput(complexData.getIdentifier(), cov);
													
							request.addDataInput(wcsInput);
							LOGGER.log(Level.INFO, "Created image input: {0}", wcsInput);
						}
					}
				}  else if (complexData.getDefaultFormat().getMimeType().toLowerCase().contains(Format.TEXT_XML)) {
					// WFS
                    for (InputForm inputForm : inputForms) {
                        FeatureInputForm featureInputForm = (FeatureInputForm) inputForm;
						if (inputForm.getInputValue() != null && inputForm.getInputValue().length() != 0) {
							
							WFSFeatureType featureType = SmartWPS.getSmartWPS().getDataTree().getWFSFeatureType(inputForm.getInputValue());
							
                            String version = featureInputForm.getVersion();
							if (!StringUtils.isNullOrEmpty(version)) {
                                featureType.setWfsVersion(version);
                            }
							
							WFSProcessInput wfsInput = new WFSProcessInput(complexData.getIdentifier(), featureType);
							request.addDataInput(wfsInput);
						}
					}
					
				}
			} else if (input instanceof LiteralData) {
				// Literal
                String inputValue = literalsForm.getValueAsString(input.getIdentifier());
				if (inputValue != null && inputValue.length() != 0) {
					//FormItem formItem = literalsForm.getItem(wpsData.getIdentifier());
					// Only add input if it isn't the default value
					//if (!value.equals(defaultValues.get(formItem))) {
						LiteralProcessInput literalInput = new LiteralProcessInput(input.getIdentifier(), inputValue);
						request.addDataInput(literalInput);
						LOGGER.log(Level.INFO, "Created literal input: {0}", literalInput);
					//}
				}
			}
		}
		
		callWPSExecute(request);
	}
	
	/**
	 * Makes a wpsExecute RPC request. Results are sent to the SmartWPS ResultsTabSet.
	 * @param request
	 */
	@SuppressWarnings("static-method")
	private void callWPSExecute(WPSExecuteRequest request) {
		SC.showPrompt("Issuing request...");
		// Set up the callback object.
		AsyncCallback<WPSExecuteResponse> callback = new AsyncCallback<WPSExecuteResponse>() {
			@Override
			public void onFailure(Throwable caught) {
				SC.clearPrompt();
				SC.say(caught.getMessage());
			}

			@Override
			public void onSuccess(WPSExecuteResponse response) {
				LOGGER.info("WPS Execute successful");
//				RunProcessWindow.this.result = response;
				SmartWPS.getSmartWPS().getResultsTabSet().addProcessResultsTab(response);
				SC.clearPrompt();
			}
		};

		// Make the call to the stock price service.
		LOGGER.info("Making WPSExecute service call");
		wpsService.wpsExecute(request, callback);
	}
	
	/**
	 * Base class for input layours. Each InputLayout can hold multiple InputForms,
	 * depending on the minOccurs and maxOccurs of the inputData.
	 * @author Jon Britton
	 *
	 */
	abstract class InputLayout extends VLayout {
		/**
		 * The maximum number of times this input can occur in the request.
		 */
		protected int maxOccurs;
		/**
		 * The process input data.
		 */
		protected WPSData inputData;
		private Button addButton;
		
		/**
		 * Create a new GUI input for the given WPS input.
		 * @param inputData the WPS input
		 */
        InputLayout(final WPSData inputData) {
			this.inputData = inputData;
			maxOccurs = inputData.getMaxOccurs();
			
			setGroupTitle(inputData.getIdentifier() + " (" + inputData.getMinOccurs() + ".." + inputData.getMaxOccurs() + ")");
			setIsGroup(true);
			
			setMembersMargin(5);
			setPadding(5);
			setAutoHeight();
			setAlign(Alignment.RIGHT);
			
			// if minOccurs is 0, we still want a control
			int minControls = Math.max(inputData.getMinOccurs(),1);
			
			// Add the forms
			for (int i = 0; i < minControls; i++) {
				addForm();
			}
			
			addButton = new Button("+");
			addButton.addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {
					addForm();
					removeMember(addButton);
					addMember(addButton);
				}
			});
			updateAddButton();
			
			this.addAttachHandler(new Handler() {
				
				@Override
				public void onAttachOrDetach(AttachEvent arg0) {
					updateAddButton();
				}
			});
		}
		
		private void updateAddButton() {
			// Add button
			if (hasMember(addButton) && (getMembers().length-1 >= maxOccurs)) {
				// Remove button
				removeMember(addButton);
			} else if (!hasMember(addButton) && (getMembers().length < maxOccurs)) {
				addMember(addButton);
			}
		}
		
		/**
		 * TODO: document
		 */
		abstract void addForm();
	}
	
	/**
	 * InputLayout for coverage inputs. Each CoverageInputLayout can hold multiple CoverageInputForms,
	 * depending on the minOccurs and maxOccurs of the inputData.
	 * @author Jon Britton
	 *
	 */
	class CoverageInputLayout extends InputLayout {
		/**
		 * TODO: document
		 * @param inputData
		 */
		CoverageInputLayout(final ComplexData inputData) {
			super(inputData);			
		}
		
		@Override
		void addForm() {
			if (this.getMembers().length < maxOccurs) {
				CoverageInputForm coverageForm = new CoverageInputForm((ComplexData) inputData);
				addMember(coverageForm);
			}
		}
	}
	
	/**
	 * InputLayout for feature inputs. Each FeatureInputLayout can hold multiple FeatureInputForms,
	 * depending on the minOccurs and maxOccurs of the inputData.
	 * @author Jon Britton
	 *
	 */
	class FeatureInputLayout extends InputLayout {

		/**
		 * TODO: document
		 * @param inputData
		 */
		FeatureInputLayout(WPSData inputData) {
			super(inputData);
		}

		@Override
		void addForm() {
			if (this.getMembers().length < maxOccurs) {
				FeatureInputForm coverageForm = new FeatureInputForm((ComplexData) inputData);
				addMember(coverageForm);
			}
		}
	}
	
	/**
	 * Base type for all input forms.
	 * @author Jon Britton
	 *
	 */
	public abstract class InputForm extends DynamicForm {
		/**
		 * TODO: document
		 */
		protected ComplexData complexData;
		
		/**
		 * TODO: document
		 * @param complexData
		 */
		public InputForm(ComplexData complexData){
			this.complexData = complexData;
			inputManager.registerInput(this);
			setBackgroundColor("#DDDDDD");
		}
		
		/**
		 * TODO: document
		 * @return the input metadata associated with this form
		 */
		public ComplexData getComplexData() {
			return complexData;
		}
		
		/**
		 * @return the value of this input
		 */
		public abstract String getInputValue();
	}
	
	/**
	 * A form for inputting a single feature type process input. 
	 * @author Jon
	 *
	 */
	public class FeatureInputForm extends InputForm {
		/**
		 * TODO: document
		 * @param inputData
		 */
		FeatureInputForm(final ComplexData inputData) {
			super(inputData);
			
			final ComboBoxItem featureChooser = new ComboBoxItem();
			featureChooser.setName(inputData.getIdentifier());
			featureChooser.setTitle(inputData.getIdentifier());
			//featureChooser.setHint(inputData.getAbstract());
			featureChooser.setHint(inputData.getTitle());
			featureChooser.setType("comboBox");
			
			// Retrieve list of features
			List<WFSFeatureType> features = SmartWPS.getSmartWPS().getDataTree().getWFSFeatureTypes();
			String[] featureNames = new String[features.size()];
			for (int i = 0; i < featureNames.length; i++) {
				featureNames[i] = features.get(i).getTypeName();
				LOGGER.log(Level.INFO, "FeatureName: {0}", featureNames[i]);
			}
			featureChooser.setValueMap(featureNames);
						
			final ComboBoxItem versionChooser = new ComboBoxItem();
			versionChooser.setName("versionChooser");
			versionChooser.setTitle("Version");
			versionChooser.setType("comboBox");
			versionChooser.setValueMap("1.0.0", "1.1.0");
			
			featureChooser.addChangedHandler(new ChangedHandler() {
				
				@Override
				public void onChanged(ChangedEvent event) {
					WFSFeatureType selectedFeature = SmartWPS.getSmartWPS().getDataTree().getWFSFeatureType(featureChooser.getValueAsString());
					if (selectedFeature != null) {
						versionChooser.setValue(selectedFeature.getWfsVersion());
					}
				}
			});
			
			setFields(featureChooser, versionChooser);
			
			setWidth100();
		}

		@Override
		public String getInputValue() {
			return this.getValueAsString(complexData.getIdentifier());
		}
		
		/**
		 * @return the version of the WFS request this input represents
		 */
		public String getVersion() {
			return this.getValueAsString("versionChooser");
		}
	}
	
	/**
	 * A form for inputting a single coverage process input. 
	 * @author Jon Britton
	 *
	 */
	class CoverageInputForm extends InputForm {
		private List<CoverageNode> coverageNodes;
		
		/**
		 * TODO: document
		 * @param inputData
		 */
		CoverageInputForm(final ComplexData inputData) {
			super(inputData);
			// This is probably WCS data
			this.complexData = inputData;
			final ComboBoxItem coverageChooser = new ComboBoxItem();
			coverageChooser.setName(complexData.getIdentifier());
			coverageChooser.setTitle(complexData.getTitle());
			coverageChooser.setHint(complexData.getAbstract());
			coverageChooser.setType("comboBox");
			
			// Retrieve list of coverages
			List<WCSCoverage> coverages = SmartWPS.getSmartWPS().getDataTree().getWCSCoverages();
			String[] coverageIDs = new String[coverages.size()];
			for (int j = 0; j < coverageIDs.length; j++) {
				coverageIDs[j] = coverages.get(j).getIdentifier();
			}
			coverageChooser.setValueMap(coverageIDs);
			
			coverageNodes = SmartWPS.getSmartWPS().getDataTree().getCoverageNodes();
			String[] coverageNames = new String[coverageNodes.size()];
			for (int i = 0; i < coverageNames.length; i++) {
				coverageNames[i] = coverageNodes.get(i).getLocalName();
			}
			coverageChooser.setValueMap(coverageNames);
						
			//final ComboBoxItem responseCRSChooser = new ComboBoxItem();
			//responseCRSChooser.setTitle(inputData.getIdentifier() + " Response CRS");
			//responseCRSChooser.setName(inputData.getIdentifier() + "ResponseCRS");
			
			//coverageChooser.addChangedHandler(new ChangedHandler() {
			//	public void onChanged(ChangedEvent event) {
			//		WCSCoverage selectedCoverage = SmartWPS.getSmartWPS().getDataTree().getWCSCoverage(getInputValue());
			//		if (selectedCoverage != null) {
			//			responseCRSChooser.setValueMap(selectedCoverage.getCoverageDescription().getResponseCRSs().toArray(new String[0]));
			//		}
			//	}
			//});
			
			setWidth100();
			//setItems(coverageChooser, responseCRSChooser);
			setItems(coverageChooser);	
		}

		@Override
		public String getInputValue() {
			return this.getValueAsString(complexData.getIdentifier());
		}
		
		//public String getResponseCRS() {
		//	return this.getValueAsString(complexData.getIdentifier() + "ResponseCRS");
		//}
	}
}
