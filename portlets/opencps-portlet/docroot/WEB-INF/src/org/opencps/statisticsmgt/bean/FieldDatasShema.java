
package org.opencps.statisticsmgt.bean;

public class FieldDatasShema {

	public FieldDatasShema() {

		// TODO Auto-generated constructor stub
	}

	public FieldDatasShema(
		String fieldLabel, String fieldKey, String fieldFomula) {

		this.setFieldFomula(fieldFomula);
		this.setFieldKey(fieldKey);
		this.setFieldLabel(fieldLabel);
	}

	public String getFieldLabel() {

		return _fieldLabel;
	}

	public void setFieldLabel(String fieldLabel) {

		this._fieldLabel = fieldLabel;
	}

	public String getFieldKey() {

		return _fieldKey;
	}

	public void setFieldKey(String fieldKey) {

		this._fieldKey = fieldKey;
	}

	public String getFieldFomula() {

		return _fieldFomula;
	}

	public void setFieldFomula(String fieldFomula) {

		this._fieldFomula = fieldFomula;
	}

	public enum DefaultFieldValues {
		$remainingNumber, $receivedNumber, $ontimeNumber, $overtimeNumber,
			$processingNumber, $delayingNumber
	}

	private String _fieldLabel;
	private String _fieldKey;
	private String _fieldFomula;

}
