//Basic Game Application
//Version 2
// Basic Object, Image, Movement
// Astronaut moves to the right.
// Threaded

//K. Chun 8/2018

//*******************************************************************************
//Import Section
//Add Java libraries needed for the game
//import java.awt.Canvas;

//Graphics Libraries
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;


//*******************************************************************************
// Class Definition Section

public class BasicGameApp implements Runnable {

   //Variable Definition Section
   //Declare the variables used in the program 
   //You can set their initial values too
   
   //Sets the width and height of the program window
	final int WIDTH = 1000;
	final int HEIGHT = 700;

   //Declare the variables needed for the graphics
	public JFrame frame;
	public Canvas canvas;
   public JPanel panel;
   
	public BufferStrategy bufferStrategy;
	public Image astroPic;
    public Image tired;
    public Image GDAWG;
    public Image Niastroid;
    public Image Tobyroid;
    public Image BackgroundPic;

   //Declare the objects used in the program
   //These are things that are made up of more than one variable type
	private Astronaut astro;
    private Astronaut Niamlikespickles;
    private Niastroid Niamthemenece;
    private Tobyroid  Tobyterror;


   // Main method definition
   // This is the code that runs first and automatically
	public static void main(String[] args) {
		BasicGameApp ex = new BasicGameApp();   //creates a new instance of the game
		new Thread(ex).start();                 //creates a threads & starts up the code in the run( ) method  
	}


   // Constructor Method
   // This has the same name as the class
   // This section is the setup portion of the program
   // Initialize your variables and construct your program objects here.
	public BasicGameApp() {
      
      setUpGraphics();
      //random strucutre
      //(int) (Math. Random() * range) + start(where the range starts)
      //range is 1-10
      int randx = (int)(Math.random() * 10)+1;
    //range 1-999
      randx = (int)(Math.random()*999)+1;

      int randy = (int)(Math.random()*699)+1;
      int randpx;
      int randpy;
      int randtx;
      int randty;
      randpx = (int)(Math.random()*999)+1;
      randpy = (int)(Math.random()*699)+1;
      randtx = (int)(Math.random()*999)+1;
      randty = (int)(Math.random()*699)+1;


                //todo make a varible rand y that generates a random number between 1-699

      //variable and objects
      //create (construct) the objects needed for the game and load up 
		astroPic = Toolkit.getDefaultToolkit().getImage("astronaut.png"); //load the picture
        tired =  Toolkit.getDefaultToolkit().getImage("ImTired.jpg");
        GDAWG = Toolkit.getDefaultToolkit().getImage("GDAWG.png");
        Niastroid = Toolkit.getDefaultToolkit().getImage("Niastroid.jpg");
        Tobyroid = Toolkit.getDefaultToolkit().getImage("Tobyroid.png");
        BackgroundPic = Toolkit.getDefaultToolkit().getImage("TheMichealsun.jpg");

		astro = new Astronaut(300,500);
        Niamlikespickles = new Astronaut(randx,randy);
        Niamthemenece = new Niastroid(400,150);
        //Niamthemenece.dx = -Niamthemenece.dx; - use this to change the dx or dy of two objects that come from the same class
        Tobyterror = new Tobyroid(100,200);


	}// BasicGameApp()

   
//*******************************************************************************
//User Method Section
//
// put your code to do things here.

   // main thread
   // this is the code that plays the game after you set things up
	public void run() {

      //for the moment we will loop things forever.
		while (true) {

         moveThings();  //move all the game objects
         render();  // paint the graphics
         pause(20); // sleep for 10 ms
		}
	}


	public void moveThings()
	{
      //calls the move( ) code in the objects
		astro.move();
        Niamlikespickles.move();
        Niamthemenece.move();
        Tobyterror.move();
        crashing();



	}

    public void crashing(){
        // check to see if my astros crash into eachother
       if(astro.hitBox.intersects(Niamlikespickles.hitBox)){
            System.out.println("CRASH!!!");
            astro.dx = -astro.dx;
            Niamlikespickles.dx = -Niamlikespickles.dx;
           astro.dy = -astro.dy;
           Niamlikespickles.dy = -Niamlikespickles.dy;





       }
        if(Niamthemenece.hitBox.intersects(Tobyterror.hitBox)&&Niamthemenece.iscrasinhg == false){
            System.out.println("BOOM!!!");
            Niamthemenece.height += 50;
            Niamthemenece.iscrasinhg = true;
            Niamthemenece.isAlive = false;





         /*/  Niamthemenece.dx = -Niamthemenece.dx;
            Niamthemenece.dx = -Tobyterror.dx;
            Niamthemenece.dy = -Niamthemenece.dy;
            Niamthemenece.dy = -Tobyterror.dy;
            /*/





        }
// ! = saying we want the oppiste thing to happen
    if(!Niamthemenece.hitBox.intersects(Tobyterror.hitBox)){
        Niamthemenece.iscrasinhg = false;

    }

    }
	
   //Pauses or sleeps the computer for the amount specified in milliseconds
   public void pause(int time ){
   		//sleep
			try {
				Thread.sleep(time);
			} catch (InterruptedException e) {

			}
   }

   //Graphics setup method
   private void setUpGraphics() {
      frame = new JFrame("Application Template");   //Create the program window or frame.  Names it.
   
      panel = (JPanel) frame.getContentPane();  //sets up a JPanel which is what goes in the frame
      panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));  //sizes the JPanel
      panel.setLayout(null);   //set the layout
   
      // creates a canvas which is a blank rectangular area of the screen onto which the application can draw
      // and trap input events (Mouse and Keyboard events)
      canvas = new Canvas();  
      canvas.setBounds(0, 0, WIDTH, HEIGHT);
      canvas.setIgnoreRepaint(true);
   
      panel.add(canvas);  // adds the canvas to the panel.
   
      // frame operations
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //makes the frame close and exit nicely
      frame.pack();  //adjusts the frame and its contents so the sizes are at their default or larger
      frame.setResizable(false);   //makes it so the frame cannot be resized
      frame.setVisible(true);      //IMPORTANT!!!  if the frame is not set to visible it will not appear on the screen!
      
      // sets up things so the screen displays images nicely.
      canvas.createBufferStrategy(2);
      bufferStrategy = canvas.getBufferStrategy();
      canvas.requestFocus();
      System.out.println("DONE graphic setup");
   
   }


	//paints things on the screen using bufferStrategy
	private void render() {
        Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
        g.clearRect(0, 0, WIDTH, HEIGHT);
        // Start adding things here
        g.drawImage(BackgroundPic, 0, 0, WIDTH, HEIGHT, null);

        //draw the image of the astronaut
        g.drawImage(astroPic, astro.xpos, astro.ypos, astro.width, astro.height, null);

        if (Niamthemenece.isAlive == true) {
            g.drawImage(Niastroid, Niamthemenece.xpos, Niamthemenece.ypos, Niamthemenece.width, Niamthemenece.height, null);
        }

            g.drawImage(astroPic, astro.xpos, astro.ypos, astro.width, astro.height, null);
            g.drawImage(GDAWG, astro.xpos, astro.ypos, astro.width, astro.height, null);
            g.drawImage(tired, Niamlikespickles.xpos, Niamlikespickles.ypos, Niamlikespickles.width, Niamlikespickles.height, null);
            g.drawImage(Tobyroid, Tobyterror.xpos, Tobyterror.ypos, Tobyterror.width, Tobyterror.height, null);
            // use this to draw hitbox
            g.drawRect(astro.hitBox.x, astro.hitBox.y, astro.hitBox.width, astro.hitBox.height);

// end of adding things
            g.dispose();

            bufferStrategy.show();
        }
        }
