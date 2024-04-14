[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-24ddc0f5d75046c5622901739e7c5dd533143b0c8e959d652212380cedb1ea36.svg)](https://classroom.github.com/a/t218cK-M)


# 4.Hafta Ödevi

## İçerik
- [Önemli Notlar](#önemli-notlar)
- [Kurulum & Çalıştırma](#kurulum)
- [HLD](#hld)
- [Örnek Kullanım](#örnek-kullanım)
- [Ödev istekleri](#ödev-istekleri)

## Önemli Notlar
1. Projeyi çalıştırdıktan sonra 1 dakika kadar bekleyin. **Bank-service** uygulaması her dakika scheduled job çalıştırıyor.
Bu işlem Garanti ve Akbank bankalarından loan bilgilerini alıp kendi dbsinde saklıyor. **Application-service** çalışabilmesi
için beklemek önemli.
2. User oluşturabilmek için önce Address oluşturun.
3. Servisle ilgili ekran görüntülerine screen-shots klasöründen ulaşabilirsiniz.

## Kurulum
Docker compose ile çalıştırmak için 

```
$ cd week-4-akbasbatuhn
$ docker compose up
```

komutlarını kullanabilirsiniz. Eğer terminali kullanmak isterseniz detached mode ile çalıştırın:

```
$ docker compose up -d
```

## HLD
![Kredinbizde HLD.png](screen-shots%2FKredinbizde%20HLD.png)

## Örnek Kullanım

Önce Address oluşturalım:

![Step-1 Create Address.png](screen-shots%2FStep-1%20Create%20Address.png)

Sonra Bir User oluşturalım:

![Step-2 Create User.png](screen-shots%2FStep-2%20Create%20User.png)

Sonra Application oluşturabiliriz:

![Step-3 Create Application.png](screen-shots%2FStep-3%20Create%20Application.png)

Artık Application database'inde verimiz saklanıyor. Hatta banka'nın database'inde de saklanıyor:

![Step-4 Get Application.png](screen-shots%2FStep-4%20Get%20Application.png)

Notification Service loglarında notificationları görebiliriz:

![notification-service.png](screen-shots%2Fnotification-service.png)

### Error Log
Olmayan veriye ulaşmaya çalışalım:

![Not found exception step 1.png](screen-shots%2FNot%20found%20exception%20step%201.png)

Error Log Service uygulamamızın logları:

![Not found exception step 2.png](screen-shots%2FNot%20found%20exception%20step%202.png)

Database'de saklanması:

![Not found exception step 3.png](screen-shots%2FNot%20found%20exception%20step%203.png)


## Ödev İstekleri
- Kullanılan servisleri istediğiniz bir database ile bağlayın. `(30 PUAN)`
- Bir uygulama için service katmanına tüm gerekli senaryolar için unit test yazın. `(20 PUAN)(Daha fazla yapan bonus 10 PUAN)`
- Tüm uygulamaları tek bir repo haline getirin.`(5 PUAN)`
- Dokümantasyon ekleyin. `(20 PUAN)`
- Best practices, isimlendirme kurallarına uyum. `(10 PUAN)`
- Uygulamanın doğru çalışması. `(15 PUAN)`

### BONUS
- Uygulamaların dockerize edilmesi ve docker compose dosyası yazılması. `(10 PUAN)`
- Design Pattern kullanımı. `(10 PUAN)`

---
*Eğitmen - Cem DIRMAN*  
*Kolay Gelsin*
