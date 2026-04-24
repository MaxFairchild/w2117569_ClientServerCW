ID: W2117569 Name: Max Arnon Fairchild

To run the application it requires to download Apache-tomcat and unzip the file In addition to use the Postman API you will need to download the postman agent, i was unable to attach the helper so instead i have attached a link

Question Part 1.1: In this program/app the object is instantiated with every request, this can be changed to use the same object by using the @Singleton annotation, Per-request is thread safe for each variable however it creates alarger overhead.

Part 1.2: The approach of hypermedia links is great for developers as it means that if a url changes it does not break the structure of a program, with static links such as ones used in my app if the url is changed slightly it can disrupot the whole program

Part 2.1: When returning a list of ids rather than the objects it decreases the payload to be communicated between the client and server, However if the API wants to display the Room names and other entities, an additional N+ API calls are made to display that information, in addition each individual API call means/requires more processing on the client side to make the API requests and process them

Part 2.2: The DELETE function is Idempotent in the idea it will have the same effect each time its run, however it cannot be in practice as the Deleted room should be gone after the first execution, after the first i.e the second execution it should have a different result displaying the room does not exist hence not idempotent.

Part 3.1: Jax-RS handles this mismatch by Returning an error 415 "Unsupported Media Type"

Part 3.2: By using QueryParam instead of a path you can identify a range of values, as using path highlights a specific instace/boject

PArt 4.1: By using a sub-resource handler it debloats the amount of processing required for each POST and GET request, instead of instantiating a whole Sensor for example it only instanties the SensorReading

Part 5.4: By exposing the entire stack trace it risks allowing a user to see/interpret another user's private information, this can range to security issues such as leaking an address or identity or potentially handing over a customer's Payment information both dangerous concerns in their own right
