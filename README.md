# Quickstart

This project will start a Camunda 7 Environment protected by Okta. 

You must have Okta configured first. Configuring Okta is outside the scope of this project, but here some notes: 

- Create Okta Developer Account
- Create a few test users and groups in Okta
- Create an Okta Application. Configure the `issuer` url, `client_id` and `client_secret` in [applicaiton.yaml](./src/main/resources/application.yaml)

Once Okta is configured, you can build and run this webapp like so: 

```
mvn build clean
mvn springboot:run
```

Then, point your browser to the [Camunda Welcome App](http://localhost:8080/camunda/app/welcome/default/#!/welcome)

This project uses spring security to protect resources. You should be redirected to an Okta sign in screen.