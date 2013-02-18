package org.yudin.wigets;

import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

public class MessageBoxFactory {
	public static void showBox(String title, String message, Shell shell) {
		MessageBox box = new MessageBox(shell);
		box.setMessage(message);
		box.setText(title);
		box.open();
	}
}
