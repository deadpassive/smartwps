package uk.ac.glam.smartwps.client.wps;

import java.util.ArrayList;
import java.util.List;

import uk.ac.glam.smartwps.client.wps.RunProcessWindow.InputForm;

/**
 * Manages process inputs for the RunProcessWindow. Helps associated UI forms with WPS inputs.
 * 
 * @author Jon Britton
 */
public class ProcessInputManager {

	private List<InputForm> inputs = new ArrayList<>();
	
	/**
	 * Register the given input form.
	 * @param form
	 */
	public void registerInput(InputForm form) {
		if (!inputs.contains(form)) {
            inputs.add(form);
        }
	}
	
	/**
	 * Get the input forms for the given input ID.
	 * @param id the input identifier
	 * @return a list of input forms for the input
	 */
	public List<InputForm> getInputs(String id) {
		ArrayList<InputForm> inputList = new ArrayList<>();
        for (InputForm inputForm : inputs) {
			if (inputForm.getComplexData().getIdentifier().equals(id)) {
                inputList.add(inputForm);
            }
		}
		return inputList;
	}
}
