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

## Users, Groups, and Authorizations

The example process assigns the first task to the `camunda-managers` group. 

Then, it assigns the second User Task directly to `bill.gates@camunda.com`. 

There is more than one way to configure security, but the way I suggest is to create a group called `tasklist-users`. Authorize the `tasklist-users` to have READ access to any filters. And create another authorization to the `tasklist-users` to have access to the `tasklist` App. 

Create 2 users: `elon.musk@camunda.com` and `bill.gates@camunda.com` and add them to the `tasklist-users`. 

Add `elon.musk@camunda.com` to `camunda-managers`. 

Now, Elon should be able to see User Task 1. And Bill should see User Task 2. 





