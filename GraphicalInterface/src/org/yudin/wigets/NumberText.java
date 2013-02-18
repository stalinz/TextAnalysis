package org.yudin.wigets;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;

public class NumberText extends Composite {
	private static final String ZERO = "0";
	private Text text;

	public NumberText(Composite arg0, int arg1) {
		super(arg0, arg1);
		setLayout(new GridLayout());

		text = new Text(this, SWT.BORDER);
		text.addListener(SWT.Verify, new Listener() {
			public void handleEvent(Event e) {
				String string = e.text;
				char[] chars = new char[string.length()];
				string.getChars(0, chars.length, chars, 0);
				for (int i = 0; i < chars.length; i++) {
					if (!('0' <= chars[i] && chars[i] <= '9')) {
						e.doit = false;
						return;
					}
				}
			}
		});
		GridData data = new GridData();
		data.grabExcessHorizontalSpace = true;
		data.grabExcessVerticalSpace = true;
		data.horizontalAlignment = GridData.FILL;
		data.verticalAlignment = GridData.FILL;

		text.setLayoutData(data);
	}

	public String getText() {
		return text.getText();
	}

	public void setText(String line) {
		text.setText(line);
	}

	public double getDouble() {
		double result;
		String val = getText();
		if (val.isEmpty())
			setText(ZERO);
		result = Double.parseDouble(getText());
		return result;
	}
}
