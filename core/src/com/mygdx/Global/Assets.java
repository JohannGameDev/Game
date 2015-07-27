package com.mygdx.Global;



import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetErrorListener;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.ObjectMap;
import com.mygdx.ObjectInfo.MonsterInfo;
import com.mygdx.ObjectInfo.ObjectSkillsInformation;
import com.mygdx.assets.AssetImpCharacter;
import com.mygdx.assets.AssetMonster;
import com.mygdx.assets.AssetSkill;
import com.mygdx.factory.AssetImpCharacterInfoFactory;

import com.mygdx.factory.MonsterInfoFactory;


/**
 * @author Johann
 * 
 */

public class Assets implements Disposable, AssetErrorListener {

	public static final String TAG = Assets.class.getName();
	public static final Assets instance = new Assets();
	private AssetManager assetManager;
	
	private TextureAtlas atlasSprites;
	public TextureAtlas atlasUI;
	public TextureAtlas atlasTiles;
	public TextureAtlas atlasSkills;
	
	public TiledMap map;
	
	public AssetCharacterRedImp assetCharacterRedImp;

	public AssetCharacterGreenImp assetCharacterGreenImp;
	
	public ObjectMap<String,AssetMonster> assetMonsterMap = new ObjectMap<String, AssetMonster>();
	public ObjectMap<String,AssetSkill> assetSkillMap = new ObjectMap<String, AssetSkill>();
	public Skin mainMenuSkin;
	

	// singleton: prevent instantiation from other classes
	private Assets() {
	}

	public void init(AssetManager assetManager) {

		this.assetManager = assetManager;
		// set asset manager error handler
		assetManager.setErrorListener(this);
		// load texture atlas
		assetManager.load(Constants.TEXTURE_ATLAS_SPRITES, TextureAtlas.class);
		// assetManager.load(Constants.TEXTURE_ATLAS_TILES, TextureAtlas.class);
		assetManager.load(Constants.TEXTURE_ATLAS_UI, TextureAtlas.class);
		
		assetManager.load(Constants.TEXTURE_ATLAS_SKILLS, TextureAtlas.class);
		//assetManager.load(Constants.MAIN_MENU_SKIN,Skin.class);
		
		assetManager.setLoader(TiledMap.class, new TmxMapLoader(
				new InternalFileHandleResolver()));
		assetManager.load("tilemap/testmap.tmx", TiledMap.class);

		// start loading assets and wait until finished
		assetManager.finishLoading();

		// once the asset manager is done loading
		map = assetManager.get("tilemap/testmap.tmx");
        
		
		atlasUI = new TextureAtlas(Gdx.files.internal("images/ui.pack"));
		atlasSprites = new TextureAtlas(Gdx.files.internal("images/sprites.pack"));
		atlasSkills = new TextureAtlas(Gdx.files.internal(Constants.TEXTURE_ATLAS_SKILLS));
		//mainMenuSkin = assetManager.get(Constants.MAIN_MENU_SKIN);
        
		Gdx.app.debug(TAG,
				"# of assets loaded: " + assetManager.getAssetNames().size);
		for (String a : assetManager.getAssetNames())
			Gdx.app.debug(TAG, "asset: " + a);

	    MonsterInfoFactory.instantiate();
		AssetImpCharacterInfoFactory.instantiate();
		
		assetCharacterGreenImp = new AssetCharacterGreenImp(atlasSprites);
		assetCharacterRedImp = new AssetCharacterRedImp(atlasSprites);

		
		

	}

	@Override
	public void dispose() {
		assetManager.dispose();
	}

	@Override
	public void error(AssetDescriptor asset, Throwable throwable) {
		Gdx.app.error(TAG, "Couldn't load asset '" + asset.fileName + "'",
				(Exception) throwable);

	}
	//Für alle skills dier der character hat wird ein assetskill angefertigt
	public void initSkills(ObjectSkillsInformation characterSkillsInformation){
		Gdx.app.debug(TAG, characterSkillsInformation.objectSkills.first().skillInfo.skillName);
		for (int i = 0; i < characterSkillsInformation.objectSkills.size; i++) {
			Gdx.app.debug(TAG, characterSkillsInformation.objectSkills.get(i).skillInfo.skillName);
			assetSkillMap.put(characterSkillsInformation.objectSkills.get(i).skillName, new AssetSkill(characterSkillsInformation.objectSkills.get(i), atlasSkills));
		}
	}
	public void initAssetMonster(Array<MonsterInfo> levelMonsterArray ){
		for (int i = 0; i < levelMonsterArray.size; i++) {
			
			assetMonsterMap.put(levelMonsterArray.get(i).monsterName,new AssetMonster(levelMonsterArray.get(i),atlasSprites)) ;
			
		}
		
		
	}

	


	
	public class AssetCharacterGreenImp extends AssetImpCharacter{
		static final String skinName = "green";
		
		public AssetCharacterGreenImp(TextureAtlas atlas) {
			super(skinName, atlas);
			
		}
		
	}
	

	public class AssetCharacterRedImp {
		// Test

		public TextureRegion texreg;

		// Animation TextureRegions
		private TextureRegion attack_PitchforkShield;
		private TextureRegion attack_Pitchfork;
		private TextureRegion attack_SwordShield;
		private TextureRegion attack_Sword;
		private TextureRegion attack_Vanilla;
		private TextureRegion walk_Vanilla;
		private TextureRegion walk_PitchforkShield;
		private TextureRegion walk_Pitchfork;
		private TextureRegion walk_SwordShield;
		private TextureRegion walk_Sword;
		private TextureRegion death_Imp;

		// Animation
		public Animation DeathImp;

		public Animation attack_VanillaUP;
		public Animation attack_VanillaDOWN;
		public Animation attack_VanillaRIGHT;
		public Animation attack_VanillaLEFT;

		public Animation attack_PitchforkUP;
		public Animation attack_PitchforkDOWN;
		public Animation attack_PitchforkRIGHT;
		public Animation attack_PitchforkLEFT;

		public Animation attack_SwordShieldUP;
		public Animation attack_SwordShieldDOWN;
		public Animation attack_SwordShieldRIGHT;
		public Animation attack_SwordShieldLEFT;

		public Animation attack_SwordUP;
		public Animation attack_SwordDOWN;
		public Animation attack_SwordRIGHT;
		public Animation attack_SwordLEFT;

		public Animation attack_PitchforkShieldUP;
		public Animation attack_PitchforkShieldDOWN;
		public Animation attack_PitchforkShieldRIGHT;
		public Animation attack_PitchforkShieldLEFT;

		public Animation walk_PitchforkShieldUP;
		public Animation walk_PitchforkShieldDOWN;
		public Animation walk_PitchforkShieldRIGHT;
		public Animation walk_PitchforkShieldLEFT;

		public Animation walk_PitchforkUP;
		public Animation walk_PitchforkDOWN;
		public Animation walk_PitchforkRIGHT;
		public Animation walk_PitchforkLEFT;

		public Animation walk_SwordUP;
		public Animation walk_SwordDOWN;
		public Animation walk_SwordRIGHT;
		public Animation walk_SwordLEFT;

		public Animation walk_SwordShieldUP;
		public Animation walk_SwordShieldDOWN;
		public Animation walk_SwordShieldRIGHT;
		public Animation walk_SwordShieldLEFT;

		public Animation walk_VanillaUP;
		public Animation walk_VanillaDOWN;
		public Animation walk_VanillaRIGHT;
		public Animation walk_VanillaLEFT;

		public AssetCharacterRedImp(TextureAtlas atlas) {

			// Walk Vanilla
			AtlasRegion atlasRegionWalkVanilla = atlas
					.findRegion("red/walk_Vanilla");
			walk_Vanilla = new TextureRegion(atlasRegionWalkVanilla);
			Animation[] all_Walk_Vanilla = createAnimation(4, 4, walk_Vanilla);// 4
																				// Different
																				// animation
																				// 4
																				// frames
																				// per
																				// animation
			walk_VanillaDOWN = all_Walk_Vanilla[0];
			walk_VanillaUP = all_Walk_Vanilla[1];
			walk_VanillaRIGHT = all_Walk_Vanilla[2];
			walk_VanillaLEFT = all_Walk_Vanilla[3];

			// Walk Sword
			AtlasRegion atlasRegionWalkSword = atlas
					.findRegion("red/walk_Sword");
			walk_Sword = new TextureRegion(atlasRegionWalkSword);
			Animation[] all_Walk_Sword = createAnimation(4, 4, walk_Sword);// 4
																			// Different
																			// animation
																			// 4
																			// frames
																			// per
																			// animation
			walk_SwordDOWN = all_Walk_Sword[0];
			walk_SwordUP = all_Walk_Sword[1];
			walk_SwordRIGHT = all_Walk_Sword[2];
			walk_SwordLEFT = all_Walk_Sword[3];
			// Walk SwordShiel
			AtlasRegion atlasRegionWalkSwordShield = atlas
					.findRegion("red/walk_SwordShield");
			walk_SwordShield = new TextureRegion(atlasRegionWalkSwordShield);
			Animation[] all_Walk_SwordShield = createAnimation(4, 4,
					walk_SwordShield);// 4 Different animation 4 frames per
										// animation
			walk_SwordShieldDOWN = all_Walk_SwordShield[0];
			walk_SwordShieldUP = all_Walk_SwordShield[1];
			walk_SwordShieldRIGHT = all_Walk_SwordShield[2];
			walk_SwordShieldLEFT = all_Walk_SwordShield[3];
			// Walk Pitchfork
			AtlasRegion atlasRegionWalkPitchfork = atlas
					.findRegion("red/walk_Pitchfork");
			walk_Pitchfork = new TextureRegion(atlasRegionWalkPitchfork);
			Animation[] all_Walk_Pitchfork = createAnimation(4, 4,
					walk_Pitchfork);// 4 Different animation 4 frames per
									// animation
			walk_PitchforkDOWN = all_Walk_Pitchfork[0];
			walk_PitchforkUP = all_Walk_Pitchfork[1];
			walk_PitchforkRIGHT = all_Walk_Pitchfork[2];
			walk_PitchforkLEFT = all_Walk_Pitchfork[3];

			// Walk PitchforkSchield
			AtlasRegion atlasRegionWalkPitchforkShield = atlas
					.findRegion("red/walk_PitchforkShield");
			walk_PitchforkShield = new TextureRegion(
					atlasRegionWalkPitchforkShield);
			Animation[] all_Walk_PitchforkShield = createAnimation(4, 4,
					walk_PitchforkShield);// 4 Different animation 4 frames per
											// animation
			walk_PitchforkShieldDOWN = all_Walk_PitchforkShield[0];
			walk_PitchforkShieldUP = all_Walk_PitchforkShield[1];
			walk_PitchforkShieldRIGHT = all_Walk_PitchforkShield[2];
			walk_PitchforkShieldLEFT = all_Walk_PitchforkShield[3];

			// Attack Vanilla
			AtlasRegion atlasRegionAttackVanilla = atlas
					.findRegion("red/attack_Vanilla");
			attack_Vanilla = new TextureRegion(atlasRegionAttackVanilla);
			Animation[] all_Attack_Vanilla = createAnimation(4, 4,
					attack_Vanilla);// 4 Different animation 4 frames per
									// animation
			attack_VanillaDOWN = all_Attack_Vanilla[0];
			attack_VanillaUP = all_Attack_Vanilla[1];
			attack_VanillaRIGHT = all_Attack_Vanilla[2];
			attack_VanillaLEFT = all_Attack_Vanilla[3];

			// Attack Sword
			AtlasRegion atlasRegionAttackSword = atlas
					.findRegion("red/attack_Sword");
			attack_Sword = new TextureRegion(atlasRegionAttackSword);
			Animation[] all_Attack_Sword = createAnimation(4, 4, attack_Sword);// 4
																				// Different
																				// animation
																				// 4
																				// frames
																				// per
																				// animation
			attack_SwordDOWN = all_Attack_Sword[0];
			attack_SwordUP = all_Attack_Sword[1];
			attack_SwordRIGHT = all_Attack_Sword[2];
			attack_SwordLEFT = all_Attack_Sword[3];

			// Attack Sword Shield
			AtlasRegion atlasRegionAttackSwordShield = atlas
					.findRegion("red/attack_SwordShield");
			attack_SwordShield = new TextureRegion(atlasRegionAttackSwordShield);
			Animation[] all_Attack_SwordShield = createAnimation(4, 4,
					attack_SwordShield);// 4 Different animation 4 frames per
										// animation
			attack_SwordShieldDOWN = all_Attack_SwordShield[0];
			attack_SwordShieldUP = all_Attack_SwordShield[1];
			attack_SwordShieldRIGHT = all_Attack_SwordShield[2];
			attack_SwordShieldLEFT = all_Attack_SwordShield[3];

			// Attack Pitchfork with Shield
			AtlasRegion atlasRegionAttackPitchforkShield = atlas
					.findRegion("red/attack_PitchforkShield");
			attack_PitchforkShield = new TextureRegion(
					atlasRegionAttackPitchforkShield);
			Animation[] all_Attack_pitchforkShield = createAnimation(4, 4,
					attack_PitchforkShield);// 4 Different animation 4 frames
											// per animation
			attack_PitchforkShieldDOWN = all_Attack_pitchforkShield[0];
			attack_PitchforkShieldUP = all_Attack_pitchforkShield[1];
			attack_PitchforkShieldRIGHT = all_Attack_pitchforkShield[2];
			attack_PitchforkShieldLEFT = all_Attack_pitchforkShield[3];
			// Attack Pitchfork
			AtlasRegion atlasRegionAttackPitchfork = atlas
					.findRegion("red/attack_Pitchfork");
			attack_Pitchfork = new TextureRegion(atlasRegionAttackPitchfork);
			Animation[] all_Attack_pitchfork = createAnimation(4, 4,
					attack_Pitchfork);// 4 Different animation 4 frames per
										// animation
			attack_PitchforkDOWN = all_Attack_pitchfork[0];
			attack_PitchforkUP = all_Attack_pitchfork[1];
			attack_PitchforkRIGHT = all_Attack_pitchfork[2];
			attack_PitchforkLEFT = all_Attack_pitchfork[3];
			// Death-Imp
			AtlasRegion atlasRegionDeathImp = atlas.findRegion("red/death_Imp");
			death_Imp = new TextureRegion(atlasRegionDeathImp);
			Animation[] all_death_Imp = createAnimation(1, 7, death_Imp);// 1
																			// Different
																			// animation
																			// 4
																			// frames
																			// per
																			// animation
			DeathImp = all_death_Imp[0];

		}

		/**
		 * @param amountAnimation
		 *            - How many animations in the TextureRegion (amount of
		 *            rows)
		 * @param framesPerAnimation
		 *            - How many frames per animation (amount of collums)
		 * @param animationTexture
		 *            - Textureregion with all the animation
		 * @return list with seperated animations
		 */
		private Animation[] createAnimation(int amountAnimation,
				int framesPerAnimation, TextureRegion animationTexture) {
			int frame_Rows = amountAnimation;
			int frame_Cols = framesPerAnimation;
			Animation[] animationList = new Animation[amountAnimation];// for
																		// return
																		// statement
			TextureRegion[][] tmp = animationTexture.split(
					animationTexture.getRegionWidth() / frame_Cols,
					animationTexture.getRegionHeight() / frame_Rows); // #10
			TextureRegion[] animationFrames = new TextureRegion[frame_Cols
					* frame_Rows];
			// 2-dimensional array to 1 -dimensional array
			int index = 0;
			for (int i = 0; i < frame_Rows; i++) {
				for (int j = 0; j < frame_Cols; j++) {
					animationFrames[index++] = tmp[i][j];
				}
			}

			// create animation from 1-dimensional-array.Count until
			// framesPeranimation has been reached and save it
			TextureRegion[] tmpKeyFrames = new TextureRegion[framesPerAnimation];
			int startPoint = 0;
			for (int k = 0; k < amountAnimation; k++) {
				for (int l = 0; l < framesPerAnimation; l++) {
					startPoint = k * framesPerAnimation;
					tmpKeyFrames[l] = animationFrames[startPoint + l];

				}
				animationList[k] = new Animation(0.2f, tmpKeyFrames);

			}
			return animationList;

		}

	}
}
