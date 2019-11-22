Name: Jia Qian Shi

The interface AnimationView is the blue print for the views of my animation. There is only a single method in this interface, setInput, which 
takes the given String and sets it as the view for the animation. 

The implementation of AnimationView is AnimationViewImpl. The constructor of this class takes in an Appendable object which acts as the output 
of the view. The string set by setInput is appended to this output. 

The interface AnimationController is the blue print for the controllers of my animation. Again there is only a single method in this interface,
run, which runs the animation and the output depends on the given String viewType, which should be either text or svg. 

The implementation of AnimationController is AnimationControllerImpl. The constructor of this class takes in an AnimationView and an 
AnimationModel. Depending on the viewType it will request either the text or svg representation of the animation from the model and pass it 
to the view via the view's setInput.

There are several significant changes to my model class AnimationModelImpl when compared to the last assignment. First of all, to get the 
text and svg String representations of the animation I had to implement two methods toText and toSVG. Since those methods require a 
ticks-per-second I had to add a new field to the constructor. To implement these two methods at first I thought to parse 
AnimationModelImpl's toString but that proved way too much of a hassle so instead I had to add several methods to my Assets and 
FrameModifaction classes to directly obtain data about the IShapes. Secondly I also implemented TweenModelBuilder as a nested class inside 
AnimationModelImpl. Appropriately named Builder, this class acts as an adapter that allows AnimationFileReader to build my model.

The main class, EasyAnimator takes in command line arguments to specify the input file, the view type, the output source, and the speed of 
the animation. The main class produces the animation by sending the appropriate model and view to the controller and letting it take over.

NEW FOR HOMEWORK 9 

MyWindow is my visual view, it's also a JFrame. It stores a MyDrawingPanel (a JPanel) that acts as the panel upon which all animation is drawn.
It also stores the JButtons that store the functionalities asked for in the extra credit. 

MyDrawingPanel stores a List of IRenderableShapes, which are shapes that can be rendered by the animation. Each such IRenderableShape acts
as a delegatee for a given IShape and pass all functionality to the IShape.

Since there are more views compared to the last assignment, I decided to create a ViewFactory which is a factory that creates either a SVGView,
a TextView, or a MyWindow depending on the passed String view type.

My AnimationControllerImpl has an extra runJFrame private method compared to HW8. It starts a swing timer that at every delay, gets a list 
of shapes from the model and pass it to the view to render, after that the model updates its current animation frame. The runJframe method 
also add ActionListeners for all JButtons in the view, giving them functionality.

As always the EasyAnimationClass acts as the main class for the animation, only difference being that the -iv output type can now be "visual",
which gives the MyWindow view.

