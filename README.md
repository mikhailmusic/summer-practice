# Hotel Booking

Летняя практика 2024 РУТ(МИИТ)  
**Предметная область: Бронирование отеля**  

**Алексеев Михаил Сергеевич**  
**УВП-211**  

*****

### Бизнес-сценарии

- [ ] 1.Подбор доступных номеров в отелях с учётом планируемого периода проживания, бюджета, вместимости, страны, города;
- [ ] 2.Создание списка рекомендованных доп. услуг на основе истории бронирования клиента;
- [ ] 3.Возможность бронирования номера с доп. услугами:  

  1) Клиент выбирает номер и может добавить доп. услуги;  
  2) Система проверяет то, что данный номер свободен на данный промежуток времени и «исправен»;  
  3) Если клиент добавляет доп. услуги итоговая цена увеличивается;  
  4) Клиенты, бронирующие за несколько месяцев до даты заезда или сделавшие больше N бронирований, получают скидку;  
  5) В системе создается платеж со статусом «СОЗДАН» на 1 час для оплаты.  
  6) Клиент производит оплату, статус платежа меняется на «ЗАВЕРШЕН», если оплата не производится в течение часа или отменена, то бронь недействительна и платеж имеет статус «ПРОСРОЧЕН» или «ОТМЕНЕН»  

- [ ] 4.Отмена брони: Клиент отменяет бронь. Если до въезда осталось более 24 часов, создается платеж со статусом «ВОЗВРАТ» и сумма полностью возвращается. Если менее 24 часов или бронирование уже началось, создается новый платеж со статусом «ВОЗВРАТ», сумма за прошедшие дни не возвращается + штраф в размере стоимости 1 ночи.


