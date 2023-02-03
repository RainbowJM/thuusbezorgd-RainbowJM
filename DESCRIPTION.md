# Thuusbezorgd 

---
## Microservices
This application consist of 3 microservices:
- Driver service
- Order service
- Stock service

In this application you will notice a few pattern that was used, when it was implemented. One of these pattern is CQRS pattern.
CQRS uses messages to describe commands, events, queries to deal with the Command Model and sync changes and extracts data from the Query model.
Each microservice has its own command and query.

RabbitMQ was used as message broker. it's responsible for the sending and receiving messages.

Each service runs on a different server port. 

The database that was used is PostgreSQL.

> For this application there was some modification made to the general configuration of docker.

### Driver Service

---
This service takes care of the delivery part, when the order service sends a message to the 
driver service, the driver service on it turn will receive the message and based on which Key was sent 
the driver service will process this. 

> In this current version of the application, the driver service listens to 2 keys
- order.event.created
- order.event.deleted

The driver service itself publish/send messages when an event occurs.

Event:
- Delivery has been created, _key: driver.event.created_
- Delivery has been deleted, _key: driver.event.deleted_
- Delivery has been updated, _key: driver.event.updated_
- Delivery has been changed, _key: driver.event.changed_

### Order Service

--- 
This service takes care of the order part,
when the driver service sends a message to the order service, the order service on it turn will receive the message
and based on which Key was sent the order service will process this.
Or when the stock service sends a message to the order service, the order service on it turn will receive the message
and based on which Key was sent the order service will process this.

> In this current version of the application, the driver service listens to 6 keys
- stock.event.increased
- stock.event.decreased
- stock.event.deleted
- stock.event.added
- driver.event.created
- driver.event.updated

The order service itself publish/send messages when an event occurs.

Event:
- Order has been created, _key: order.event.created_
- Order has been cancelled, _key: order.event.deleted_
- Order status has been changed, _key: order.event.changed-status_

### Stock Service

---
This service takes care of the stock part, basically the ingredients that is needed to make orders,
when the order service sends a message to the stock service, the stock service on it turn will receive the message 
and based on which Key was sent the stock service will process this.

> In this current version of the application, the driver service listens to 2 keys
- order.event.created
- order.event.deleted

The stock service itself publish/send messages when an event occurs.

Event:
- Ingredient has been added to the stock list, _key: stock.event.added_
- Ingredient has been removed from the stock list, _key: stock.event.deleted_
- Ingredient has been restocked, _key: stock.event.increased_
- Ingredient has been used to make order, _key: stock.event.decreased_