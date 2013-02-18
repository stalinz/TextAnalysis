package org.yudin.wigets;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

public class LimitWiget extends Composite {
	private Label nameLabel;
	private Label percentLabel;
	private NumberText value;

	public LimitWiget(Composite arg0, int arg1, String label) {
		super(arg0, arg1);
		GridLayout layout = new GridLayout();
		layout.numColumns = 3;
		setLayout(layout);
		initNameLabel(label);
		initTextField();
		initPercentLabel();

	}

	private void initNameLabel(String label) {
		nameLabel = new Label(this, SWT.NONE);
		nameLabel.setText(label);
		GridData data = new GridData();
		data.horizontalAlignment = GridData.BEGINNING;
		nameLabel.setLayoutData(data);
	}

	private void initPercentLabel() {
		percentLabel = new Label(this, SWT.NONE);
		percentLabel.setText("%");
		GridData data = new GridData();
		data.horizontalAlignment = GridData.BEGINNING;
		percentLabel.setLayoutData(data);
	}

	private void initTextField() {
		value = new NumberText(this, SWT.NONE);
		GridData data = new GridData();
		data.horizontalAlignment = GridData.FILL;
		data.grabExcessVerticalSpace = true;
		value.setLayoutData(data);
	}

	public double getValue() {
		return value.getDouble();
	}
}
