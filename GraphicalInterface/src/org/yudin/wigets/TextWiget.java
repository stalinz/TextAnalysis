package org.yudin.wigets;

import java.io.IOException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Text;
import org.yudin.analyzer.StringFactory;

public class TextWiget extends Composite {
	private Text multiText;
	private Button loadButton;

	public TextWiget(Composite arg0, int arg1) {
		super(arg0, arg1);
		setLayout(new GridLayout());
		initButton();
		initMultiLineTextBlock();
	}

	private void initMultiLineTextBlock() {
		multiText = new Text(this, SWT.MULTI | SWT.V_SCROLL | SWT.H_SCROLL
				| SWT.BORDER);
		GridData data = new GridData();
		data.grabExcessHorizontalSpace = true;
		data.grabExcessVerticalSpace = true;
		data.horizontalAlignment = GridData.FILL;
		data.verticalAlignment = GridData.FILL;
		multiText.setLayoutData(data);

	}

	private void initButton() {
		loadButton = new Button(this, SWT.PUSH);
		GridData data = new GridData();
		data.grabExcessHorizontalSpace = true;
		data.horizontalAlignment = GridData.END;
		loadButton.addMouseListener(loadButtonPushed);
		loadButton.setLayoutData(data);
		loadButton.setText("Загрузить текст из файла");

	}

	@Override
	public String toString() {
		return multiText.getText();
	};

	MouseListener loadButtonPushed = new MouseListener() {

		@Override
		public void mouseUp(MouseEvent arg0) {
			FileDialog openDialog = new FileDialog(getShell(), SWT.OPEN);
			String path = openDialog.open();
			if (path != null && !path.isEmpty()) {
				try {
					multiText.setText(StringFactory.readFile(path));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		@Override
		public void mouseDown(MouseEvent arg0) {
		}

		@Override
		public void mouseDoubleClick(MouseEvent arg0) {
		}
	};
}
