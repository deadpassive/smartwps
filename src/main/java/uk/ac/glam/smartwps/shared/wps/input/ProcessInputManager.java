package uk.ac.glam.smartwps.shared.wps.input;

import java.util.ArrayList;
import java.util.Iterator;

import uk.ac.glam.smartwps.client.wps.RunProcessWindow.InputForm;

public class ProcessInputManager {

	//private ArrayList<CoverageInputForm> coverageInputs = new ArrayList<CoverageInputForm>();
	//private ArrayList<FeatureInputForm> featureInputs = new ArrayList<FeatureInputForm>();
	private ArrayList<InputForm> inputs = new ArrayList<InputForm>();
	
	//public void registerInput(CoverageInputForm form) {
	//	coverageInputs.add(form);
	//}

	public void registerInput(InputForm form) {
		if (!inputs.contains(form))
			inputs.add(form);
	}
	
	/**
	 * Returns the value of this input. If there are more than one input with the same ID, this returns the first.
	 * @param inputID
	 * @return
	 */
	public String getFirstInputValue(String inputID) {
		for (Iterator<InputForm> iterator = inputs.iterator(); iterator.hasNext();) {
			InputForm form = iterator.next();
			String inputValue = form.getInputValue();
			return inputValue;
		}
		return null;
	}
	
	public ArrayList<InputForm> getInputs(String id) {
		ArrayList<InputForm> inputList = new ArrayList<InputForm>();
		for (Iterator<InputForm> iterator = inputs.iterator(); iterator.hasNext();) {
			InputForm inputForm = iterator.next();
			if (inputForm.getComplexData().getIdentifier().equals(id))
				inputList.add(inputForm);
		}
		return inputList;
	}
}
