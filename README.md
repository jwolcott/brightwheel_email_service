# brightwheel_email_service
Welcome to my service to provide failover support for sending emails. This scala service has an endpoint for sending an email and supports choosing which email provider to use via configuration. Additionally, if the chosen email provider does not succeed, it will attempt the other one.

# Languge/frameworks used
This project is written in Scala with the help of Dropwizard and Retrofit. These were chosen because they're technologies that I'm familiar with and time was of the essence.

# So does it work?
Sadly, there was nowhere near enough time to actually get the entire service up and running. There's simply a lot to do when building a service completely from scratch. I'm guessing I could get it running with a little more time, I simply chose to put as much logic as possible in instead of prioritizing getting it to run.

# What about tests?
Due to time constraints, there are no actual tests. However, I have laid out some testcases that I would have implemented given more time.

# Further thoughts on the project
Honestly I'm not convinced that this demonstrates very much. At a real company that already has existing services, this would have been much faster to write since I could model my code after existing patterns. On the other hand if this was the first such service, I can't say that I would have necessarily chosen to use any of the same technologies. I'm aware that Dropwizard for scala is not being maintained, and I'm not sure Retrofit was the right choice either. So in a real world situation I wouldn't have necessarily done things the same way at all. 

# Special note about the failover
I admit that I did not follow instructions about the failover switch via configuration. My approach is a first pass for touchless failover, since manually switching providers anytime one goes down would be a big burden on on-call engineers. Instead, I'd much rather implement an automated solution that detects when one service is failing and switches to the other. Further iteration would have me looking at counting success/failure rates and attempting to switch providers automatically.