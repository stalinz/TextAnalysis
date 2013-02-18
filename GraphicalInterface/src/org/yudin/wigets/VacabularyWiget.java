package org.yudin.wigets;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yudin.vacabulary.IVacabulary;
import org.yudin.vacabulary.StandartVacabulary;

public class VacabularyWiget extends Composite implements IVacabulary {
	private static final String SUCCESS = "Успешно";
	private static final String WORDS = " слов";
	private static final String LOADED = "Загружено ";
	private static final String SIZE_OF_NEW_VACABULARY = "Size of new vacabulary {}";
	private static final String NOT_SELECTED_YET = "[Словарь не выбран]";
	private static final String LOAD_TEXT = "Загрузить";
	private static final String VACABULARY = "Словарь: ";

	private static Logger log = LoggerFactory.getLogger(VacabularyWiget.class);

	private Label vacabularyNameLabel;
	private IVacabulary vacabulary = new StandartVacabulary();
	private int vacabularySize;

	public VacabularyWiget(Composite arg0, int arg1) {
		super(arg0, arg1);
		GridLayout layout = new GridLayout();
		layout.numColumns = 3;
		this.setLayout(layout);

		initVacabularyLabel();

		vacabularyNameLabel = initVacabularyNameLabel();

		initLoadButton();
	}

	private Label initVacabularyLabel() {
		Label label = new Label(this, SWT.NONE);
		GridData gridData = new GridData();
		gridData.horizontalAlignment = GridData.BEGINNING;
		gridData.verticalAlignment = GridData.CENTER;
		label.setLayoutData(gridData);
		label.setText(VACABULARY);
		return label;
	}

	private Label initVacabularyNameLabel() {
		Label label = new Label(this, SWT.NONE);
		GridData gridData = new GridData();
		gridData.horizontalAlignment = GridData.FILL;
		gridData.verticalAlignment = GridData.CENTER;
		gridData.grabExcessHorizontalSpace = true;
		label.setLayoutData(gridData);
		label.setText(NOT_SELECTED_YET);
		return label;

	}

	private Button initLoadButton() {
		Button button = new Button(this, SWT.PUSH);
		GridData gridData = new GridData();
		gridData.horizontalAlignment = GridData.END;
		gridData.verticalAlignment = GridData.CENTER;
		button.setText(LOAD_TEXT);
		button.addMouseListener(loadButtonListener);
		return button;
	}

	private MouseListener loadButtonListener = new MouseListener() {

		@Override
		public void mouseUp(MouseEvent arg0) {
			FileDialog openDialog = new FileDialog(getShell(), SWT.OPEN);
			String path = openDialog.open();
			File file = new File(path);

			vacabularySize = loadFromStream(loadProcedure(file));
			log.debug(SIZE_OF_NEW_VACABULARY, vacabularySize);
			MessageBox box = new MessageBox(getShell(), SWT.OK);
			box.setMessage(LOADED + vacabularySize + WORDS);
			box.setText(SUCCESS);
			box.open();
		}

		@Override
		public void mouseDown(MouseEvent arg0) {
		}

		@Override
		public void mouseDoubleClick(MouseEvent arg0) {
		}
	};

	public InputStream loadProcedure(File file) {
		InputStream result = null;
		vacabularyNameLabel.setText(file.getName());
		try {
			result = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public boolean findWord(String word) {
		return vacabulary.findWord(word);
	}

	@Override
	public boolean addWord(String word) {
		return vacabulary.addWord(word);

	}

	@Override
	public int loadFromStream(InputStream in) {
		vacabulary = new StandartVacabulary();
		return vacabulary.loadFromStream(in);
	}

	@Override
	public boolean saveToStream(OutputStream out) {
		return vacabulary.saveToStream(out);
	}

	@Override
	public void addVacabulary(IVacabulary anotherVacabulary) {
		vacabulary.addVacabulary(anotherVacabulary);

	}

	@Override
	public List<?> allWords() {
		return vacabulary.allWords();
	}

	public int getVacabularySize() {
		return vacabularySize;
	}
}
