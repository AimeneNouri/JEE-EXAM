# JEE-EXAM
## 1.Présentation de l'activité pratique

Création d'une application basée sur une architecture micro-service qui permet de gérer les factures contenant des produits et appartenant à un client.

        1. Gateway Service
        
        2. Eureka Service
        
        3. Customer Service
        
        4. Inventory Service
        
        5. Billing Service
        
        6. Frontend Web avec Angular

***
***

## 2.Conception de l'application
* Entité "Product" :
```java
  @Data @AllArgsConstructor @NoArgsConstructor @Entity @ToString
  public class Product {
      @Id
      @GeneratedValue(strategy = GenerationType.IDENTITY)
      private Long id;
      private String name;
      private double price;
      private double quantity;
  }
```

* Entité "Customer" :
```java
  @Entity
  @Data @AllArgsConstructor @NoArgsConstructor @ToString
  public class Customer {
      @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
      private Long id;
      private String name;
      private String email;
  }
```

* Entité "Bills" :
```java
  @Entity @AllArgsConstructor @NoArgsConstructor @Data @ToString @Builder
  public class Bill {
      @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
      private Long id;
      private Date billingDate;
      private BillStatus status;
      @OneToMany(mappedBy = "bill")
      private Collection<ProductItem> productItems;
      private long customerID;
      @Transient
      private Customer customer;

      public double getTotal(){
          return productItems.stream()
                  .mapToDouble(ProductItem::getTotal).sum();
      }
  }
```

***
*** 

## 3.Architecture de l'activité pratique
![architecture](https://user-images.githubusercontent.com/48455549/206859150-63e5c806-86a2-4937-8791-9a7ce2464316.PNG)


### Service Product :

![files_inventory](https://user-images.githubusercontent.com/48455549/206859239-81407bc3-1b99-4997-b186-132c4a64a5a4.PNG)

![product-service](https://user-images.githubusercontent.com/48455549/206859254-23904f2a-4ab4-4fa8-affb-e7298051ca50.PNG)

![products](https://user-images.githubusercontent.com/48455549/206859314-ffae3d66-6af6-4ccf-a6c9-773e717ec269.PNG)

***

### Service Customer :

![files_customer](https://user-images.githubusercontent.com/48455549/206859352-42743f0e-b29c-4e12-9b8d-3f28f1acb3cc.PNG)

![customers](https://user-images.githubusercontent.com/48455549/206859562-a4e606dd-7867-4265-9cd9-54a5f81bd116.PNG)

![gateway-service_customer_dynamic](https://user-images.githubusercontent.com/48455549/206859399-41e71cc7-6e94-468e-ad9a-f5e5778fba9e.PNG)

***

### Service Gateway :

![files_gateway](https://user-images.githubusercontent.com/48455549/206859774-3224a9b7-dcc3-4da9-a64b-3ecebcb59dba.PNG)


> Routage Statique 

* using Yaml

![gateway_routage_statique](https://user-images.githubusercontent.com/48455549/206859784-8c7a27f0-420f-4450-9e95-9da54922a192.PNG)

* using code JAVA

![gateway_static](https://user-images.githubusercontent.com/48455549/206859858-2970658f-fc7d-4f74-99ad-ffd1939f5cfa.png)


> Routage Dynamique

![gateway_dynamic](https://user-images.githubusercontent.com/48455549/206859783-bc92aade-8cee-43ce-9045-43cafddf1aaf.PNG)

***

### Service Eureka :

![eureka-service](https://user-images.githubusercontent.com/48455549/206859924-0c135888-af17-4bef-a1d2-0ff60e072638.PNG)

![eureka](https://user-images.githubusercontent.com/48455549/206860074-d888440c-3d54-423d-a97a-8bb5a56ec846.PNG)

***

### Service Billing :

![billing](https://user-images.githubusercontent.com/48455549/206860186-dc878139-6c44-486d-8495-016e4e57c3b5.PNG)

![bills](https://user-images.githubusercontent.com/48455549/206860458-44288e2f-705b-4664-94d8-84da13e842c3.PNG)

![bills1](https://user-images.githubusercontent.com/48455549/206860469-9c7d90bb-6bb6-473b-9363-9c523183728d.PNG)

![bills3](https://user-images.githubusercontent.com/48455549/206860472-904bc70f-7787-4e88-873c-59b011ae4ed9.PNG)

***

### Frontend Angular

* Listes des produits

![products-frontend](https://user-images.githubusercontent.com/48455549/206860811-51b7366d-1914-4b34-a94d-9c67e0c63957.PNG)


* Listes des clients

![customers-frontend](https://user-images.githubusercontent.com/48455549/206860824-e516698e-9baa-468f-9161-6e561fb87fd4.PNG)


* Listes des factures d'un client

![bills-frontend](https://user-images.githubusercontent.com/48455549/206860834-f716fd5a-d343-4dee-a37b-50f51c2b19c7.PNG)



* Details d'une facture d'un client

![bill-details-frontend](https://user-images.githubusercontent.com/48455549/206860868-7de3a474-9569-466f-989c-8dc3df2c33a8.PNG)



