<br>________________________________________________________________________________________________

### How to use the program:

There are two ways to run the program:
- one with a GUI (Recommended)
- one without a GUI

#### Program with GUI
To run the program with a GUI, run the program from the file "ImageProcessingWGUI"

1. Start by loading an image by clicking "load" and selecting a valid image (jpeg, png, etc.)
2. Click other buttons to change and modify the image
3. Click the save button to save the image to a local location


#### Program without GUI
To run the program without a GUI, run the program from the file "ImageProcessing"

When the program starts, the terminal will prompt, "What would you like to do?"
The user must use the load command first before attempting to use any image-editing command,
otherwise, the image-editing commands will do nothing. - Ex: "load res/techsupport.ppm tech".

##### Commands that the program accepts:

1. "load [String directory path to image] [String name of img]"
    - loads an image into view
    - Ex: "load res/techsupport.ppm tech"
2. "brighten [integer amount] [String targeted img name] [String desired name of new img]"
    - brightens an image by an integer amount
    - Ex: "brighten 100 tech techBrighten"
3. "vertical-flip [String targeted img name] [String desired name of new img]"
    - vertically flips an image
    - Ex: "vertical-flip tech techFlipped"
4. "horizontal-flip [String targeted img name] [String desired name of new img]"
    - horizontally flips an image
    - Ex: "horizontal-flip tech techFlipped2"
5. "red-component [String targeted img name] [String desired name of new img]"
    - greyscales the image according to the red-component value of each pixel
    - Ex: "red-component tech techRed"
6. "green-component [String targeted img name] [String desired name of new img]"
    - greyscales the image according to the green-component value of each pixel
    - Ex: "green-component tech techGreen"
7. "blue-component [String targeted img name] [String desired name of new img]"
    - greyscales the image according to the blue-component value of each pixel
    - Ex: "blue-component tech techBlue"
8. "value-component [String targeted img name] [String desired name of new img]"
    - greyscales the image according to the value-component value of each pixel (the value-component
      is the maximum value between the red/green/blue components)
    - Ex: "value-component tech techValue"
9. "intensity-component [String targeted img name] [String desired name of new img]"
    - greyscales the image according to the intensity-component value of each pixel
      (the intensity-component is the average value between the red/green/blue components)
    - Ex: "intensity-component tech techIntensity"
10. "luma-component [String targeted img name] [String desired name of new img]"
    - greyscales the image according to the luma-component value of each pixel
      (luma is a weighted average between the rgb component values)
    - Ex: "luma-component tech techLuma"
11. "greyscale-luma [String targeted img name] [String desired name of new img]"
    - converts the image into greyscale using luma component computation
    - Ex: "greyscale-luma nyc grescyalenyc"
12. "sepia [String targeted img name] [String desired name of new img]"
    - places a sepia-tone filter over the image
    - Ex: "sepia nyc sepianyc"
13. "save [String desired directory path for new image]
    [String name of image created]"
    - saves the desired image to the computer
    - Ex: "save res/techsupportLuma.ppm techLuma"
14. "q" - quits and ends the program

<br>After an attempted command input is given, a success message or an error message will appear if
the
command was executed properly or not. More image-editing commands may be executed over the duration
of the program.

If the user wants to quit the program, the user should input "q" in response to the question
"What would you like to do?"

<br>Important Notes:

- "q" should be treated like its own command and should not be attempted as an
  argument of a different command. Ex: Do not: "vertical flip q" - this will not work
- Spaces between each word/[] is placed with purpose
- To darken image, one should use the brighten command but with negative integer values
