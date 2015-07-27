package com.mygdx.OwnInputProcessor;

import java.util.Iterator;

import SaveHelper.SlotHelper;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.mygdx.Global.Assets;
import com.mygdx.game.GameUml;
import com.mygdx.screens.GameScreen;
import com.mygdx.screens.SlotScreen;

public class SlotScreenStage {
	private static final String TAG = SlotScreenStage.class.getName();
	Stage slotScreenStage;

	Array<Container> slotGroup = new Array<Container>();
	GameUml game;
	public Table slotContent = new Table();
	public Table wholeContent = new Table();
	public int currentSlotSelected;
	
	private Skin skin;

	public SlotScreenStage(GameUml game) {
		this.game = game;
		init();
	}

	private void init() {
		skin = new Skin(Gdx.files.internal("userInterfaceSkins/myUiSkin.json"),
				Assets.instance.atlasUI);
		// Generate a 1x1 white texture and store it in the skin named "white".
		Pixmap pixmap = new Pixmap(1, 1, Format.RGBA8888);
		pixmap.setColor(Color.WHITE);
		pixmap.fill();
		skin.add("white", new Texture(pixmap));
		slotScreenStage = new Stage(new ExtendViewport(Gdx.graphics.getWidth(),
				Gdx.graphics.getHeight()));
		SlotHelper.init();
		initSlotContent();
		wholeContent.debug();
		ScrollPane scroller = new ScrollPane(slotContent);

		ImageButton loadSlotButton = new ImageButton(skin,
				"loadSlotButtonStyle");
		loadSlotButton.pad(10);
	
		ImageButton newSlotButton = new ImageButton(skin, "newSlotButtonStyle");
		newSlotButton.pad(10);
	
		ImageButton backToMainMenuButton = new ImageButton(skin,
				"backToMainMenuStyle");
		backToMainMenuButton.pad(10);
		
		wholeContent.setFillParent(true);
         //setze ausgewählten Slot in slothelper und starte gameScreen
		loadSlotButton.addListener(new ClickListener() {
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				SlotHelper.setDebugCurrentSlot(currentSlotSelected);
				game.setScreen(new GameScreen(game));
				slotScreenStage.clear();
				return true;

			}
		});

		HorizontalGroup buttonHorizontal = new HorizontalGroup();
		buttonHorizontal.addActor(loadSlotButton);
		buttonHorizontal.pad(10);
		buttonHorizontal.addActor(newSlotButton);
		buttonHorizontal.addActor(backToMainMenuButton);
		buttonHorizontal.space(10);
        
		Container buttonContent = new Container(buttonHorizontal);
		wholeContent.add(buttonContent);

		wholeContent.row();

		wholeContent.pad(10);
		wholeContent.add(scroller).expand().fill().left().bottom().pad(20);
		
		wholeContent.setBackground(skin.getDrawable("paper_Background"));

		slotScreenStage.addActor(wholeContent);

	}

	private void initSlotContent() {

		// für jeden slot erstelle eine horizontalle gruppe unnd speicher sie in
		// einem Array
		for (int i = 0; i < SlotHelper.slotArray.size; i++) {
			HorizontalGroup tmpDisplayInfo; // ein Slot als horizontalegruppe
			Image tmpImage;// bilde vom character im speicherplatz
			Label tmpLabel;// anzeigen von informationen aus slot objekt
			String tmpInfo;// zeichenkette für das label

			tmpDisplayInfo = new HorizontalGroup();
			tmpImage = new Image(skin,
					SlotHelper.slotArray.get(i).imageButtonTag);
			tmpInfo = "Level " + SlotHelper.slotArray.get(i).level
					+ " CharacterName : "
					+ SlotHelper.slotArray.get(i).characterName;
			tmpLabel = new Label(tmpInfo, skin, "labelSlotStyle");
			tmpDisplayInfo.addActor(tmpImage);
			tmpDisplayInfo.addActor(tmpLabel);
			tmpDisplayInfo.space(10);
			tmpDisplayInfo.setName("");
			final Container container = new Container(tmpDisplayInfo);
			container.align(Align.left);
			container.setName("" + i);
			tmpDisplayInfo.addListener(new ClickListener() {
				public boolean touchDown(InputEvent event, float x, float y,
						int pointer, int button) {
					
					currentSlotSelected = Integer.parseInt(container.getName());

					for (int j = 0; j < slotGroup.size; j++) {
						slotGroup.get(j).setBackground(
								skin.getDrawable("paper_Background"));

					}
					
					container.setBackground(skin.newDrawable("white",
							Color.DARK_GRAY));
					return true;

				}
			});

			slotGroup.add(container);// Array Hinzufügen
		}
		// füge jede horizontale gruppe die jeweils ein slot enthält einer
		// tabelle(slotContent) hinzu
		for (Iterator<Container> iterator = slotGroup.iterator(); iterator
				.hasNext();) {
			Container group = (Container) iterator.next();

			slotContent.add(group).align(Align.left).expandX().fillX();

			slotContent.row();
		}
	}

	public Stage getSlotScreenStage() {

		return slotScreenStage;
	}

}
