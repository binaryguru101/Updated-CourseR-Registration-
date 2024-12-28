Added generic class of feedback which student can either rate a course by giving it a number or simply write a message in strings. 
The professor is able to see his/her feedback only if they manage that class.

The TA class extends the student class which extends the user class but the TA has more fliexbilty over things such as seeing students in a course(if they manage it). Similiarly professors are also able to see the TA's appointed under their handled course furthermore only admin can assign a TA to a specific course although there is no restriction that the TA mustve completed the course before. 

Exceptions are handling throughout. I did not have an attribute in my courses that told a specific deadline date and instead of adding and manually fixing and adding attributes, I created a final static date in the courses section that only the Admin can modify. If a student tries to drop it before, he/she can except when the deadline is over it raises a custom error handling message. 

Similarly course full exceptions are handled and other login exceptions such as wrong password. In addition to this both admin student and professor(Ta aswell) also have custom course not found/course not handling exceptions