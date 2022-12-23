# user-manager-example

# Test the Application

you can test it by using curl or some similar tool. You have three HTTP endpoints that you can test:

POST localhost:8080/user: Adds one user to the database. 
GET localhost:8080/user/{n}: Gets n number of user data.
GET localhost:8080/user/ages/{n}: Gets user name and ages whose age is greater than n. 

The following curl command adds a user:

```
$ curl localhost:8080/user -d name=jhondoe -d email=someemail@someemailprovider.com -d dob=08-03-1997
```

The reply should be as follows:

Saved

The following command shows n number of users:

```
$ curl 'localhost:8080/user/1'
```

The reply should be as follows:

[{"id":1,"name":"jhondoe","email":"someemail@someemailprovider.com","dob":"08-03-1997"}]

The following command shows user name and ages whose age is greater than n:

```
$ curl 'localhost:8080/user/ages/20'
```
The reply should be as follows:

{"jhondoe":25}
