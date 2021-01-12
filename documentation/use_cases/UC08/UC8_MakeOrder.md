# UC8 - Make Order

## 1. Requirements Engineering

The client starts to make an order. The system shows the items in the shopping cart and the total price and asks to confirm. The client confirms. The system shows the address where the order will be sent to and asks to confirm. The client confirms. The system asks to select the payment method (credit card or credits). The client selects the desired method. The system creates the order, send the invoice to the client's email and informs about the sucess of the operation.
### SSD
![UC8_SSD.png](UC8_SSD.png)

#### Main Actor

Client

#### Stakeholders and their interests
* **Client:** wants to make orders and receive it in home.
* **Phamarcy:** wants to sell its products.


#### Pre Conditions
The client must be logged in as client.
The client should have at least one product in the sohhping cart.

#### Post Conditions
An email with the invoice is sent to client.

## 2. Engineering Design

### Sequence Diagram

![UC8_SD](UC8_SD.png)

![CheckPharmacyStock_SD](SD_CheckPharmactStock.png)


### Class Diagram

![UC8_CD](UC8_CD.png)