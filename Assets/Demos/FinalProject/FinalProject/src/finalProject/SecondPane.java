package finalProject;
/*Name: Jessy Francisco
 * CIN: 307259828
 * Course/Section: CS2012 Section 1 and 2
 * Description: This class the GUI and main part of the game*/
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

import java.util.Random;

public class SecondPane extends GridPane
{
	private Random r = new Random();
	private int rows, columns;
	private String[][] tracker;
	private ImageView boss, koopaRed, spiny, goomba, cannon, spinner, fireBall, ammo1, ammo2, player,player2,player3, playerI;
	private String[] sprites = new String[10];
	private ImageView[] playerIg = new ImageView[4];
	private String[] enemies = new String[3];
	private String[] traps = new String[3];
	private ImageView[] images = new ImageView[10];
	private int hitter = 0, playersLives = 2, ammo = 3, seltRows = 0, seltCols = 0, bossRows = 0, bossCols = 0,counter = 0;
	private Scene mainScene;
	private Stage mainStage, st;
	private ToolBar menu;
	private boolean done = false;
	
	public SecondPane(int rows, int columns)
	{
		this.rows = rows;
		this.columns = columns;
		
		//TO KEEP TRACK FOR GAME
		this.tracker = preArena(this.rows,this.columns);
		
		for (int i = 0; i < this.columns; i++) {
            ColumnConstraints colConst = new ColumnConstraints();
            colConst.setPercentWidth(100.0 / this.columns);
            this.getColumnConstraints().add(colConst);
        }
        for (int i = 0; i < this.rows; i++) {
            RowConstraints rowConst = new RowConstraints();
            rowConst.setPercentHeight(100.0 / this.rows);
            this.getRowConstraints().add(rowConst);         
        }
        this.setGridLinesVisible(true);
        int sizeW = this.rows * 100;
        int sizeH = this.rows * 100;
        this.mainScene = new Scene(this,sizeW,sizeH);
		this.mainScene.setOnKeyPressed(key ->
		{
			switch(key.getCode())
			{
			case LEFT:
				this.moveSpriteX(-1);
				break;
			case RIGHT:
				this.moveSpriteX(1);
				break;
			case UP:
				this.moveSpriteY(-1);
				break;
			case DOWN:
				this.moveSpriteY(1);
				break;
			case A:
				if(this.ammo > 0)
				{
				this.done = shootLeftRight(-1);
				}
				else
				{
					new Ammo();
				}
				if(!this.done && this.ammo != 0)
				{
					this.ammo--;
					//this.moveBossRoom();
				}
				if(this.done)
				{
					this.mainStage.hide();
					this.st.hide();
				}
				break;
			case D:
				if(this.ammo > 0)
				{
				this.done = shootLeftRight(1);
				}
				else
				{
					new Ammo();
				}
				if(!this.done && this.ammo != 0)
				{
					this.ammo--;
					//this.moveBossRoom();
				}
				if(this.done)
				{
					this.mainStage.hide();
					this.st.hide();
				}
				break;
			case W:
				if(this.ammo > 0)
				{
				this.done = shootUpDown(-1);
				}
				else
				{
					new Ammo();
				}
				if(!this.done && this.ammo != 0)
				{
					this.ammo--;
					//this.moveBossRoom();
				}
				if(this.done)
				{
					this.mainStage.hide();
					this.st.hide();
				}
				break;
			case S:
				if(this.ammo > 0)
				{
				this.done = shootUpDown(1);
				}
				else
				{
					new Ammo();
				}
				if(!this.done && this.ammo != 0)
				{
					this.ammo--;
					//this.moveBossRoom();
				}
				if(this.done)
				{
					this.mainStage.hide();
					this.st.hide();
				}
				break;
			}
		});
		this.mainStage = new Stage();
		this.mainStage.setTitle("GOOD LUCK!");
		this.mainStage.setScene(this.mainScene);
		this.mainStage.setMaximized(true);
		this.mainStage.show();
        this.setBackground();
        this.setGame();
        this.menuBar();
        
        int printWins = this.checkSurroundings();
		int boss = checkBoss();
		if(boss == 0)
		{
			new BossNotice();
		}
		else if(printWins == 1)
		{
			new MonsterNotice();
		}
		else if(printWins == 2)
		{
			new TrapNotice();
		}
		else if(printWins == 3)
		{
			new MonsterNotice();
			new TrapNotice();
		}
	}
	
	public void menuBar()
	{
		this.menu = new ToolBar();
		BorderPane p = new BorderPane();
		Button b1 = new Button("Debug On/Off");
		b1.setOnAction(e ->{
			this.turnOpacity();
		});
		Button b2 = new Button("Save Data");
		b2.setOnAction(e -> {
			SaveData data = new SaveData();
			data.setArr(this.tracker);
			data.getArr();
			data.setSetCols(this.columns);
			data.setSetRows(this.rows);
			data.setSetSelfCols(this.seltCols);
			data.setSetSelfRows(this.seltRows);	
			try {
                ResourceManager.save(data, "1.save");
            }
            catch (Exception ek) {
                System.out.println("Couldn't save: " + ek.getMessage());
            }
		});
		Button b3 = new Button("Load Data");
		new String("");
		b3.setOnAction(e ->{
			 try {
	                SaveData data = (SaveData) ResourceManager.load("1.save");
	                this.mainStage.hide();
	                this.st.hide();
	                new ThirdPane(data.getSetRows(),data.getSetCols(),data.getSetSelfRows(),data.getSetSelfCols(),data.getArr());
	            }
	            catch (Exception ek) {
	                System.out.println("Couldn't load save data: " + ek.getMessage());
	            }
		});
		this.menu.getItems().addAll(new Separator(), b1, new Separator(),b2,b3, new Separator());
		p.setTop(this.menu);
		Scene scene = new Scene(p,400,400);
		st = new Stage();
		st.setScene(scene);
		st.setTitle("GameBar");
		st.show();
	}

	private void setBackground()
	{
		Image image = new Image("mario.png");
		ImageView img = new ImageView(image);
		img.setOpacity(0.45);
		img.fitWidthProperty().bind(this.widthProperty());
		img.fitHeightProperty().bind(this.heightProperty().multiply(2));
		this.getChildren().add(img);
	}
	
	private void setGame()
	{
		this.boss = new ImageView(new Image("Sprites/Boss/Bowser.gif"));
		this.boss.setOpacity(0);
		this.boss.setFitHeight(100.0);
		this.boss.setFitWidth(100.0);
		String bossW = "bowser";
		this.sprites[0] = bossW;
		this.images[0] = boss;
		
		this.koopaRed = new ImageView(new Image("Sprites/Enemies/KoopaParatroopaRed.gif"));
		this.koopaRed.setOpacity(0);
		this.koopaRed.setFitHeight(100.0);
		this.koopaRed.setFitWidth(100.0);
		String koopaRedW = "koopa";
		this.sprites[1] = koopaRedW;
		this.enemies[0] = koopaRedW;
		this.images[1] = this.koopaRed;
		
		this.spiny = new ImageView(new Image("Sprites/Enemies/spiny.gif"));
		this.spiny.setOpacity(0);
		this.spiny.setFitHeight(50.0);
		this.spiny.setFitWidth(50.0);
		String spinyW = "spiny";
		this.sprites[2] = spinyW;
		this.enemies[1] = spinyW;
		this.images[2] = this.spiny;
		
		this.goomba = new ImageView(new Image("Sprites/Enemies/LittleGoomba.gif"));
		this.goomba.setOpacity(0);
		this.goomba.setFitHeight(50.0);
		this.goomba.setFitWidth(50.0);
		String goombaW = "goomba";
		this.sprites[3] = goombaW;
		this.enemies[2] = goombaW;
		this.images[3] = this.goomba;
		
		this.cannon = new ImageView(new Image("Sprites/Traps/CannonSmall.png"));
		this.cannon.setOpacity(0);
		this.cannon.setFitHeight(50.0);
		this.cannon.setFitWidth(50.0);
		String cannonW = "cannon";
		this.sprites[4] = cannonW;
		this.traps[0] = cannonW;
		this.images[4] = this.cannon;
		
		this.fireBall = new ImageView(new Image("Sprites/Traps/FireBallHit.gif"));
		this.fireBall.setOpacity(0);
		this.fireBall.setFitHeight(50.0);
		this.fireBall.setFitWidth(50.0);
		String fireBallW = "fireball";
		this.sprites[5] = fireBallW;
		this.traps[1] = fireBallW;
		this.images[5] = this.fireBall;
		
		this.spinner = new ImageView(new Image("Sprites/Traps/SpinningFireBalls.gif"));
		this.spinner.setOpacity(0);
		this.spinner.setFitHeight(100.0);
		this.spinner.setFitWidth(100.0);
		String spinnerW = "spinner";
		this.sprites[6] = spinnerW;
		this.traps[2] = spinnerW;
		this.images[6] = this.spinner;
		
		this.ammo1 = new ImageView(new Image("Sprites/Ammunition/KoopaTroopaShellGreen.png"));
		//this..setOpacity(0);
		this.ammo1.setFitHeight(50.0);
		this.ammo1.setFitWidth(50.0);
		String ammoW1 = "ammo1";
		this.sprites[7] = ammoW1;
		this.images[7] = this.ammo1;
		
		this.ammo2 = new ImageView(new Image("Sprites/Ammunition/KoopaTroopaShellGreen.png"));
		//this.boss.setOpacity(0);
		this.ammo2.setFitHeight(50.0);
		this.ammo2.setFitWidth(50.0);
		String ammoW2 = "ammo2";
		this.sprites[8] = ammoW2;
		this.images[8] = this.ammo2;
		
		this.player = new ImageView(new Image("Sprites/player/FieryMarioStanding.png"));
		this.player.setFitHeight(100.0);
		this.player.setFitWidth(50.0);
		String playerW = "player";
		this.sprites[9] = playerW;
		this.images[9] = this.player;
		
		this.playerIg[2] = this.player;
		this.player2 = new ImageView(new Image("Sprites/player/SuperMarioStanding.png"));
		this.player2.setFitHeight(100.0);
		this.player2.setFitWidth(50.0);
		this.playerIg[1] = this.player2;
		this.setHalignment(this.player2,HPos.CENTER);
		
		this.player3 = new ImageView(new Image("Sprites/player/MarioStanding.png"));
		this.player3.setFitHeight(50.0);
		this.player3.setFitWidth(35.0);
		this.playerIg[0] = this.player3;
		this.setHalignment(this.player3,HPos.CENTER);
		
		this.playerI = new ImageView(new Image("Sprites/player/InvincibleFieryMario.gif"));
		this.playerI.setFitHeight(100.0);
		this.playerI.setFitWidth(50.0);
		this.playerIg[3] = this.playerI;
		this.setHalignment(this.playerI, HPos.CENTER);
		setSprites();
	
	}
		
	private String[][] preArena(int rows,int cols)
	{
		String[][] list = new String[rows][cols];
		for(int r = 0; r < list.length; r++)
		{
			for(int c = 0; c < list[r].length;c++)
			{
				list[r][c] = "-";
			}
		}
		return list;
	}
	
	private void setSprites()
	{
			int setRow = r.nextInt(this.rows);
			int setCol = r.nextInt(this.columns);
			
			
			for(int i = 0; i < this.sprites.length; i++)
			{
				while(this.tracker[setRow][setCol] != "-")
				{
					setRow = r.nextInt(this.rows);
					setCol = r.nextInt(this.columns);
				}
				this.tracker[setRow][setCol] = this.sprites[i];
				
				if(this.images[i].equals(this.playerIg[this.playersLives]))
				{
					this.add(this.playerIg[this.playersLives], setCol, setRow);
					this.seltRows = setRow;
					this.seltCols = setCol;
				}
				else 
				{
				this.add(this.images[i], setCol, setRow);
				this.setHalignment(this.images[i], HPos.CENTER);
				}	
			}
			
			for(int r = 0; r < this.tracker.length; r++)
			{
				for(int c = 0; c < this.tracker[r].length; c++)
				{
					if(tracker[r][c] == this.sprites[0])
					{
						this.bossCols = r;
						this.bossRows = c;
					}
						
				}
			}
		checkRestraints();
	}
		
	private void checkRestraints()
	{
		int eraseM = r.nextInt(3);
		int eraseT = 0;
		
		if(this.rows == 5 && this.columns == 5)
		{
			this.getChildren().remove(this.playerIg[this.playersLives]);
			this.playersLives--;
			this.add(this.playerIg[this.playersLives],this.seltCols,this.seltRows);
			
			for(int r = 0; r < this.tracker.length; r++)
			{
				for(int c = 0; c < this.tracker[r].length; c++)
				{
					if(this.tracker[r][c] == this.enemies[eraseM])
					{
						this.tracker[r][c] = "-";
					}
					
					if(this.tracker[r][c].equals(this.sprites[4]))
					{
						this.tracker[r][c] = "-";
					}
					
					if(this.tracker[r][c].equals(this.sprites[5]))
					{
						this.tracker[r][c] = "-";
					}
				}
			}	
			
			eraseT = 0;
			for(int i = 0; i < this.images.length; i++)
			{
				if(this.sprites[i] == this.enemies[eraseM])
				{
					this.getChildren().remove(this.images[i]);
				}
				
				else if(this.sprites[i] == this.traps[eraseT])
				{
					this.getChildren().remove(this.images[i]);
					if(eraseT < 1)
					{
						eraseT++;
					}
				}
			}
		}
		
		else if(this.rows == 7 && this.columns == 7)
		{
			this.getChildren().remove(this.playerIg[this.playersLives]);
			this.playersLives--;
			this.add(this.playerIg[this.playersLives],this.seltCols,this.seltRows);
			
			for(int r = 0; r < this.tracker.length; r++)
			{
				for(int c = 0; c < this.tracker[r].length; c++)
				{
					if(this.tracker[r][c] == this.enemies[eraseM])
					{
						this.tracker[r][c] = "-";
					}
					else if(this.tracker[r][c].equals(this.sprites[4]))
					{
						this.tracker[r][c] = "-";
					}
				}
			}	
			
			for(int i = 0; i < this.images.length; i++)
			{
				if(this.sprites[i] == this.enemies[eraseM])
				{
					this.getChildren().remove(this.images[i]);
				}
				
				else if(this.sprites[i] == this.traps[eraseT])
				{
					this.getChildren().remove(this.images[i]);
				}
			}
		}
	}

	/**Move the sprite of the player left or right**/
	public void moveSpriteX(int x)
	{
		int pHit = 0;
		int tDead = 0;
		int ammoG = 0;
		int printWins = 0;
		int boss = 3;
		boolean alreadyDead = false;
		
		for(int r = 0; r < this.tracker.length; r++)
		{
			for(int c = 0; c < this.tracker[r].length; c++)
			{
				if(r == this.seltRows && c == this.seltCols)
				{
					tracker[r][c] = "-";
				}
			}
		}
		
		this.getChildren().remove(this.playerIg[this.playersLives]);
		
		if(x == -1)
		{
			this.seltCols--;
			
			outerloop:
				for(int r = 0; r < this.tracker.length; r++)
				{
					if(this.seltCols < 0)
					{
						this.seltCols++;
						this.tracker[this.seltRows][this.seltCols] = this.sprites[9];
						this.add(this.playerIg[this.playersLives],seltCols,seltRows);
						break;
					}
					for(int c = 0; c < this.tracker[r].length; c++)
					{
						if(c == this.seltCols && r == this.seltRows)
						{
							if(tracker[r][c] != "-")
							{
								pHit = checkEnemies(this.seltRows,this.seltCols);
								tDead = checkTraps(this.seltRows,this.seltCols);
								ammoG = checkAmmo(this.seltRows,this.seltCols);
								boss = checkBoss();
								if(pHit  == 1)
								{
									this.playersLives--;
									if(this.playersLives >= 0 )
									{
										
										this.tracker[this.seltRows][this.seltCols] = this.sprites[9];
										this.getChildren().remove(this.images[this.hitter + 1]);
										this.add(this.playerIg[this.playersLives], this.seltCols, this.seltRows);
									}
									else{
										new Lose();
										alreadyDead = true;
										this.mainStage.hide();
										this.st.hide();
										
									}
										
								}
								else if(tDead == 1)
								{
									new Lose();
									alreadyDead = true;
									this.mainStage.hide();
								}
								else if(ammoG == 1)
								{
									new StarPane();
									ammoG = 0;
									this.ammo++;
									this.tracker[this.seltRows][this.seltCols] = this.sprites[9];
									this.add(this.playerIg[this.playersLives],this.seltCols,this.seltRows);
								}
								else if(boss == 1)
								{
									new Lose();
									alreadyDead = true;
									this.mainStage.hide();
									this.st.hide();
								}
								else{
								break outerloop;
								}
							}
							else
							{
								this.tracker[this.seltRows][this.seltCols] = this.sprites[9];
								this.add(this.playerIg[this.playersLives],this.seltCols,this.seltRows);
							}
						}
					}
				 }
		}
		
		if(x == 1)
		{
			this.seltCols += 1;

			outerloop:
				for(int r = 0; r < this.tracker.length; r++)
				{
					if(this.seltCols > (this.tracker[r].length - 1))
					{
						this.seltCols -= 1;
						this.tracker[seltRows][this.seltCols] = this.sprites[9];
						this.add(this.playerIg[this.playersLives],this.seltCols,seltRows);
						break;
					}
					for(int c = 0; c < this.tracker[r].length; c++)
					{
						if(c == this.seltCols && r == this.seltRows)
						{
							if(tracker[r][c] != "-")
							{
								pHit = checkEnemies(this.seltRows,this.seltCols);
								tDead  = checkTraps(this.seltRows,this.seltCols);
								ammoG = checkAmmo(this.seltRows,this.seltCols);
								boss = checkBoss();
								if(pHit == 1 )
								{
									this.playersLives--;
									if(this.playersLives >= 0)
									{
										this.tracker[this.seltRows][this.seltCols] = this.sprites[9];
										this.getChildren().remove(this.images[this.hitter + 1]);
										this.add(this.playerIg[this.playersLives], this.seltCols, this.seltRows);
									}
									else
									{
										new Lose();
										alreadyDead = true;
										this.mainStage.hide();
									}
								}
								else if(tDead == 1)
								{
									new Lose();
									alreadyDead = true;
									this.mainStage.hide();
								}
								else if(ammoG == 1)
								{
									new StarPane();
									ammoG = 0;
									this.ammo++;
									this.tracker[this.seltRows][this.seltCols] = this.sprites[9];
									this.add(this.playerIg[this.playersLives],this.seltCols,this.seltRows);
								}
								else if(boss == 1)
								{
									new Lose();
									alreadyDead = true;
									this.mainStage.hide();
									this.st.hide();
								}
								else{
								break outerloop;
								}
							}
							else
							{
								this.tracker[this.seltRows][this.seltCols] = this.sprites[9];
								this.add(this.playerIg[this.playersLives],this.seltCols,this.seltRows);
							}
						}
					}
				}
		}
		
		if(!alreadyDead)
		{
			printWins = this.checkSurroundings();
			boss = checkBoss();
			if(boss == 0)
			{
				new BossNotice();
			}
			else if(printWins == 1)
			{
				new MonsterNotice();
			}
			else if(printWins == 2)
			{
				new TrapNotice();
			}
			else if(printWins == 3)
			{
				new MonsterNotice();
				new TrapNotice();
			}
		}
	}

	/**Move the sprite of the player up or down**/
	public void moveSpriteY(int y)
	{
		int pHit = 0;
		int tDead = 0;
		int printWins = 0;
		int ammoG = 0;
		int boss = 3;
		boolean alreadyDead = false;
		
		
		//Find Player --> Remove Player from Tracker Array
		for(int r = 0; r < this.tracker.length; r++)
		{
			for(int c = 0; c < this.tracker[r].length; c++)
			{
				if(r == this.seltRows && c == this.seltCols)
				{
					this.tracker[this.seltRows][this.seltCols] = "-";
				}
			}
		}
		
		//Remove Player Image from GridPane
		this.getChildren().remove(this.playerIg[this.playersLives]);
		
		//PLAYER GOES UP
		if(y == -1)
		{
			this.seltRows--;
			
			outerloop:
			for(int r = 0; r < this.tracker.length; r++)
			{
				//Checks for Boundaries
				if(this.seltRows < 0)
				{
					this.seltRows++;
					this.tracker[this.seltRows][this.seltCols] = this.sprites[9];
					this.add(this.playerIg[this.playersLives],this.seltCols,this.seltRows);
					break;
				}
				for(int c = 0; c < this.tracker[r].length; c++)
				{
					if(r == this.seltRows && c == this.seltCols)
					{
						//Checks if PLAYER Has DIED to ENEMY or TRAP
						if(this.tracker[this.seltRows][this.seltCols] != "-")
						{
							pHit = checkEnemies(this.seltRows,this.seltCols);
							tDead = checkTraps(this.seltRows,this.seltCols);
							ammoG = checkAmmo(this.seltRows,this.seltCols);
							boss = checkBoss();
							if(pHit == 1)
							{
								this.playersLives--;
								if(this.playersLives >= 0)
								{
									this.tracker[this.seltRows][this.seltCols] = this.sprites[9];
									this.getChildren().remove(this.images[this.hitter + 1]);
									this.add(this.playerIg[this.playersLives], this.seltCols, this.seltRows);
								}
								else
								{
									new Lose();
									alreadyDead = true;
									this.mainStage.hide();
								}
							}
							else if(tDead == 1)
							{
								new Lose();
								alreadyDead = true;
								this.mainStage.hide();
							}
							else if(ammoG == 1)
							{
								new StarPane();
								ammoG = 0;
								this.ammo++;
								this.tracker[this.seltRows][this.seltCols] = this.sprites[9];
								this.add(this.playerIg[this.playersLives],this.seltCols,this.seltRows);
							}
							else if(boss == 1)
							{
								new Lose();
								alreadyDead = true;
								this.mainStage.hide();
								this.st.hide();
							}
							else{
								break outerloop;
							}
							
						}
						else
						{
							this.tracker[this.seltRows][this.seltCols] = this.sprites[9];
							this.add(this.playerIg[this.playersLives],this.seltCols,this.seltRows);
						}
					}
				}
			}
		}
		
		//PLAYER goes DOWN
		if(y == 1)
		{
			this.seltRows++;
			
			outerloop:
			for(int r = 0; r < this.tracker.length; r++)
			{
				//PLAYER HITS A BOUNDARY
				if(this.seltRows > (this.tracker.length - 1))
				{
					this.seltRows--;
					this.tracker[this.seltRows][this.seltCols] = this.sprites[9];
					this.add(this.playerIg[this.playersLives],this.seltCols,this.seltRows);
					break;
				}
				for(int c = 0; c < this.tracker[r].length; c++)
				{
					if(r == this.seltRows && c == this.seltCols)
					{
						if(this.tracker[this.seltRows][this.seltCols] != "-")
						{
							//PLAYER HAS DIED TO ENEMY OR TRAP
							pHit = checkEnemies(this.seltRows,this.seltCols);
							tDead = checkTraps(this.seltRows,this.seltCols);
							ammoG = checkAmmo(this.seltRows,this.seltCols);
							boss = checkBoss();
							if(tDead  == 1)
							{
								new Lose();
								alreadyDead = true;
								this.mainStage.hide();
							}
							else if(pHit == 1)
							{
								this.playersLives--;
								if(this.playersLives >= 0)
								{
									this.tracker[this.seltRows][this.seltCols] = this.sprites[9];
									this.getChildren().remove(this.images[this.hitter + 1]);
									this.add(this.playerIg[this.playersLives], this.seltCols, this.seltRows);
								}
								else
								{
									new Lose();
									alreadyDead = true;
									this.mainStage.hide();
								}
							}
							else if(ammoG == 1)
							{
								new StarPane();
								ammoG = 0;
								this.ammo++;
								this.tracker[this.seltRows][this.seltCols] = this.sprites[9];
								this.add(this.playerIg[this.playersLives],this.seltCols,this.seltRows);
							}
							else if(boss == 1)
							{
								new Lose();
								alreadyDead = true;
								this.mainStage.hide();
								this.st.hide();
							}
							else{
							break outerloop;
							}
						}
						else
						{
							this.tracker[this.seltRows][this.seltCols] = this.sprites[9];
							this.add(this.playerIg[this.playersLives],this.seltCols,this.seltRows);
						}
					}
				}
			}
		}
		
		//CHECKS TO SEE IF PLAYER IS NEAR ENEMY OR TRAP
		if(!alreadyDead)
		{
			printWins = this.checkSurroundings();
			boss = checkBoss();
			if(boss == 0)
			{
				new BossNotice();
			}
			else if(printWins == 1)
			{
				new MonsterNotice();
			}
			else if(printWins == 2)
			{
				new TrapNotice();
			}
			else if(printWins == 3)
			{
				new MonsterNotice();
				new TrapNotice();
			}
		}
			
	}

	public int checkSurroundings()
	{
		int playerRow = 0, playerCol = 0;
		int pE = 0, pT = 0, fP = 0;
		for(int r = 0; r < this.tracker.length; r++)
		{
			for(int c = 0; c < this.tracker[r].length; c++)
			{
				if(tracker[r][c] == this.sprites[9])
				{
					playerRow = r;
					playerCol = c;
				}
			}
		}
		
		pE = checkEnemies(playerRow,playerCol);
		pT = checkTraps(playerRow,playerCol);
		
		//System.out.println("PE " + pE);
		//System.out.println("PT " + pT);
		if(pE == 0 && pT == 2)
		{
			fP = 1;
		}
		else if(pT == 0 && pE == 2)
		{
			fP = 2;
		}
		else if(pE == 0 && pT == 0)
		{
			fP = 3;
		}
		return fP;
	}
	
	public int checkTraps(int pX, int pY)
	{
		int death = 2;
		boolean cent = false,top = false, bottom = false, right = false, left = false;
		
		top = xTop(pX,pY,this.traps);
		//System.out.println("TOP " + top);
		
		bottom = xBottom(pX,pY,this.traps);
		//System.out.println("BOTTOM " + bottom);
		
		left = yLeft(pX,pY,this.traps);
		//System.out.println("LEFT " + left);
		
		right = yRight(pX,pY,this.traps);
		//System.out.println("RIGHT " + right);
		
		cent = centerXY(pX,pY,this.traps);
		
		if((top || bottom || right || left) && !cent )
		{
			death = 0;
		}
		
		
		else if(cent)
		{
			death = 1;
		}
		return death;
	}
	
	public int checkEnemies(int pX,int pY)
	{
		int hit = 2;
		boolean cent = false,top = false, bottom = false, right = false, left = false;
		
		top = xTop(pX,pY,this.enemies);
		//System.out.println("TOP " + top);
		
		bottom = xBottom(pX,pY,this.enemies);
		//System.out.println("BOTTOM " + bottom);
		
		left = yLeft(pX,pY,this.enemies);
		//System.out.println("LEFT " + left);
		
		right = yRight(pX,pY,this.enemies);
		//System.out.println("RIGHT " + right);
		
		cent = centerXY(pX,pY,this.enemies);
		
		if((top || bottom || right || left) && !cent )
		{
			hit = 0;
		}
		
		
		else if(cent)
		{
			hit = 1;
		}
		return hit;
	}
	
	public int checkAmmo(int pX, int pY)
	{
		String remove1 = "-";
		int ammo1Loc = 0;
		String remove2 = "-";
		int ammo2Loc = 0;
		int yee = 0;
		
		for(int i = 0; i < this.sprites.length; i++)
		{
			if(this.sprites[i].equals("ammo1"))
			{
				remove1 = this.sprites[i];
				ammo1Loc = i;
			}
			else if(this.sprites[i].equals("ammo2"))
			{
				remove2 = this.sprites[i];
				ammo2Loc = i;
			}
		}
		
		for(int r = 0; r < this.tracker.length; r++)
		{
			for(int c = 0; c < this.tracker[r].length; c++)
			{
				if(pX == r && pY == c)
				{
					if(this.tracker[pX][pY] == remove1)
					{
						yee = 1;
						this.sprites[ammo1Loc] = "-";
						this.getChildren().remove(this.images[ammo1Loc]);
					}
					else if(this.tracker[pX][pY] == remove2)
					{
						yee = 1;
						this.sprites[ammo2Loc] = "-";
						this.getChildren().remove(this.images[ammo2Loc]);
					}
				}
			}
		}
		return yee;
	}
	public boolean shootUpDown(int y)
	{
		int up = this.seltRows;
		int down = this.seltRows;
		boolean hit = false;
		if(y == -1)
		{
			up--;
			outerloop:
				for(int r = 0; r < this.tracker.length; r++)
				{
					if(up < 0)
					{
						new Miss();
						break outerloop;
					}
					for(int c = 0; c < this.tracker[r].length; c++)
					{
						if(c == this.seltCols && r == up)
						{
							if(tracker[r][c] == "-")
							{
								new Miss();
							}
							else
							{
								if(this.tracker[r][c].equals(this.sprites[0]))
								{
									new Hit();
									this.tracker[r][c] = "-";
									this.getChildren().remove(this.images[0]);
									hit = true;
								}
								else
								{
								new Miss();	
								}
							}
						}
					}
				 }
		}
		
		if(y == 1)
		{
			down++;
			outerloop:
				for(int r = 0; r < this.tracker.length; r++)
				{
					if(down > this.tracker[r].length -  1)
					{
						new Miss();
						break outerloop;
					}
					for(int c = 0; c < this.tracker[r].length; c++)
					{
						if(c == this.seltCols && r == down)
						{
							if(tracker[r][c] == "-")
							{
								new Miss();
							}
							else
							{
								if(this.tracker[r][c].equals(this.sprites[0]))
								{
									new Hit();
									this.tracker[r][c] = "-";
									this.getChildren().remove(this.images[0]);
									hit = true;
								}
								else
								{
								new Miss();	
								}
							}
						}
					}
				 }
		}
		return hit;
	}
	public boolean shootLeftRight(int x)
	{
		int left = this.seltCols;
		int right = this.seltCols;
		boolean hit = false;
		if(x == -1)
		{
			left--;
			outerloop:
				for(int r = 0; r < this.tracker.length; r++)
				{
					if(left < 0)
					{
						new Miss();
						break outerloop;
					}
					for(int c = 0; c < this.tracker[r].length; c++)
					{
						if(c == left && r == this.seltRows)
						{
							if(tracker[r][c] == "-")
							{
								new Miss();
							}
							else
							{
								if(this.tracker[r][c].equals(this.sprites[0]))
								{
									new Hit();
									this.tracker[r][c] = "-";
									this.getChildren().remove(this.images[0]);
									hit = true;
								}
								else
								{
								new Miss();	
								}
							}
						}
					}
				 }
		}
		
		if(x == 1)
		{
			right++;
			outerloop:
				for(int r = 0; r < this.tracker.length; r++)
				{
					if(right > this.tracker[r].length -  1)
					{
						new Miss();
						break outerloop;
					}
					for(int c = 0; c < this.tracker[r].length; c++)
					{
						if(c == right && r == this.seltRows)
						{
							if(tracker[r][c] == "-")
							{
								new Miss();
							}
							else
							{
								if(this.tracker[r][c].equals(this.sprites[0]))
								{
									new Hit();
									this.tracker[r][c] = "-";
									this.getChildren().remove(this.images[0]);
									hit = true;
								}
								else
								{
								new Miss();	
								}
							}
						}
					}
				 }
		}
		return hit;
		
	}
	
	
	public int checkBoss()
	{
		int hit = 2;
		boolean cent = false,top = false, bottom = false, right = false, left = false;
		
		top = xBossTop();
		
		bottom = xBossBottom();
		
		left = yBossLeft();
		
		right = yBossRight();
		
		cent = centerBossXY();
		
		if((top || bottom || right || left) && !cent )
		{
			hit = 0;
		}
		
		
		else if(cent)
		{
			hit = 1;
		}
		return hit;
	}
	
	private void turnOpacity()
	{
		if(counter % 2 == 0)
		{
			for(int i = 0; i < 7;i++)
			{
				this.images[i].setOpacity(1.0);
			}
		}
		else if(counter % 2 ==1)
		{
			for(int i = 0; i < 7; i++)
			{
				this.images[i].setOpacity(0);
			}
		}
		this.counter++;
	}

	
	private boolean xBossTop()
	{
		boolean yup = false;
		outerloop:
			for(int r = 0; r < this.tracker.length; r++)
			{
				for(int c = 0; c < this.tracker[r].length; c++)
				{
					if(this.seltRows == r && this.seltCols == c)
					{
						if(this.seltRows - 1 >= 0)
						{
							for(int i = 0; i < this.sprites.length; i++)
							{
								if(this.tracker[this.seltRows - 1][this.seltCols].equals(sprites[0]))
								{
									yup = true;
									break outerloop;
								}
							}
						}
					}
				}
			}
		return yup;
	}
	private boolean xBossBottom()
	{
		boolean yup = false;
		outerloop:
			for(int r = 0; r < this.tracker.length; r++)
			{
				for(int c = 0; c < this.tracker[r].length; c++)
				{
					if(this.seltRows == r && this.seltCols == c)
					{
						if(this.seltRows + 1 <= (this.tracker.length - 1))
						{
							for(int i = 0; i < this.sprites.length; i++)
							{
								if(this.tracker[this.seltRows + 1][this.seltCols] == sprites[0])
								{
									yup = true;
									break outerloop;
								}
							}
						}
					}
				}
			}
		return yup;	
	}
	private boolean yBossLeft()
	{
		boolean yup = false;
		outerloop:
			for(int r = 0; r < this.tracker.length; r++)
			{
				for(int c = 0; c < this.tracker[r].length; c++)
				{
					if(this.seltRows == r && this.seltCols == c)
					{
						if(this.seltCols - 1 >= 0)
						{
							for(int i = 0; i < this.sprites.length; i++)
							{
								if(this.tracker[this.seltRows][this.seltCols - 1] == this.sprites[0])
								{
									yup = true;
									break outerloop;
								}
							}
						}
					}
				}
			}
		return yup;	}
	private boolean yBossRight()
	{
		boolean yup = false;
		outerloop:
			for(int r = 0; r < this.tracker.length; r++)
			{
				for(int c = 0; c < this.tracker[r].length; c++)
				{
					if(this.seltRows == r && this.seltCols == c)
					{
						if(this.seltCols + 1 <= (this.tracker[r].length - 1))
						{
							for(int i = 0; i < this.sprites.length; i++)
							{
								if(this.tracker[this.seltRows][this.seltCols + 1] == this.sprites[0])
								{
									yup = true;
									break outerloop;
								}
							}
						}
					}
				}
			}
		return yup;
	}
	private boolean centerBossXY()
	{
		boolean yup = false;
		outerloop:
			for(int r = 0; r < this.tracker.length; r++)
			{
				for(int c = 0; c < this.tracker[r].length; c++)
				{
					if(this.seltRows == r && this.seltCols == c)
					{
						for(int i = 0; i < this.sprites.length; i++)
						{
							if(this.tracker[this.seltRows][this.seltCols] == this.sprites[0])
							{
								yup = true;
								break outerloop;
							}
						}
					}
				}
			}
		return yup;	}
	
	private boolean centerXY(int pX, int pY, String[] arr)
	{
		boolean yup = false;
		outerloop:
			for(int r = 0; r < this.tracker.length; r++)
			{
				for(int c = 0; c < this.tracker[r].length; c++)
				{
					if(pX == r && pY == c)
					{
						for(int i = 0; i < arr.length; i++)
						{
							if(this.tracker[pX][pY] == arr[i])
							{
								yup = true;
								this.hitter = i;
								break outerloop;
							}
						}
					}
				}
			}
		return yup;
	}	
	private boolean xTop(int pX, int pY,String[] arr)
	{
		boolean yup = false;
		outerloop:
			for(int r = 0; r < this.tracker.length; r++)
			{
				for(int c = 0; c < this.tracker[r].length; c++)
				{
					if(pX == r && pY == c)
					{
						if(pX - 1 >= 0)
						{
							for(int i = 0; i < arr.length; i++)
							{
								if(this.tracker[pX -1][pY] == arr[i])
								{
									yup = true;
									break outerloop;
								}
							}
						}
					}
				}
			}
		return yup;
	}
	private boolean xBottom(int pX, int pY, String[] arr)
	{
		boolean yup = false;
		outerloop:
			for(int r = 0; r < this.tracker.length; r++)
			{
				for(int c = 0; c < this.tracker[r].length; c++)
				{
					if(pX == r && pY == c)
					{
						if(pX + 1 <= (this.tracker.length - 1))
						{
							for(int i = 0; i < arr.length; i++)
							{
								if(this.tracker[pX + 1][pY] == arr[i])
								{
									yup = true;
									break outerloop;
								}
							}
						}
					}
				}
			}
		return yup;
	}
	private boolean yLeft(int pX, int pY, String[] arr)
	{
		boolean yup = false;
		outerloop:
			for(int r = 0; r < this.tracker.length; r++)
			{
				for(int c = 0; c < this.tracker[r].length; c++)
				{
					if(pX == r && pY == c)
					{
						if(pY - 1 >= 0)
						{
							for(int i = 0; i < arr.length; i++)
							{
								if(this.tracker[pX][pY - 1] == arr[i])
								{
									yup = true;
									break outerloop;
								}
							}
						}
					}
				}
			}
		return yup;
	}
	private boolean yRight(int pX, int pY, String[] arr)
	{
		boolean yup = false;
		outerloop:
			for(int r = 0; r < this.tracker.length; r++)
			{
				for(int c = 0; c < this.tracker[r].length; c++)
				{
					if(pX == r && pY == c)
					{
						if(pY + 1 <= (this.tracker[r].length - 1))
						{
							for(int i = 0; i < arr.length; i++)
							{
								if(this.tracker[pX][pY + 1] == arr[i])
								{
									yup = true;
									break outerloop;
								}
							}
						}
					}
				}
			}
		return yup;
		
	}
}
