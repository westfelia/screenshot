# Screenshot Service
This program is intended to be used as a backend for saving screenshots of webpages.
It takes as input a list of URLs and creates a screenshot for each.

## Use
Tbd

## Initial Implementation Plan

- Create a single class that takes one url and outputs one screenshot
    - Use Selenium web testing framework to render and screenshot
    - Tests: Have a few known websites (google, msn, etc.) and visually compare the screenshots. If they are satisfactory, save these as samples and then write tests to programatically compare images.
- Create database to store the images
    - Use a relational database (url, date, path) that stores the path to the image
        - This is because blobs for images aren't very practical. It also allows more to be done to the file system operations.
    - Create file system to store the images
        - Perhaps make the screenshot output the path to store it
    - Tests: For a set of images, insert them then retrieve them. Ensure that they are the proper images.
- Decouple the class: Make one service to fetch the webpage info, and another to render/screnshot
    - Connect these, perhaps first through piping.
    - Connect database to this
    - Create main to handle this
- Implement message queue for scalability

