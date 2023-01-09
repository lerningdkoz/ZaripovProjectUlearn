# ZaripovProjectUlearn
1. Создаем проект на основе Maven.

2. Подтягиваем зависимости логгера, jdbc-драйвера и либы для построения графиков(jfree).

3. Создаем в пакете src подпакет Data, в котором будет храниться класс для хранения данных из csv и сервис, который, соответсвенно, бдует отвечать за манипуляцию с этими данными.

![image](https://user-images.githubusercontent.com/95271008/210787473-3a5c79ab-46bf-4c7b-ad47-3a25aeb3b029.png)

4. Инициализируем необходимые поля в классе Employee пакета Data.
![image](https://user-images.githubusercontent.com/95271008/210787698-8ec57c99-61e6-44ef-94bd-360e34a524d9.png)

5. Переропределяем метод toString(), для корректного вывода данных.
![image](https://user-images.githubusercontent.com/95271008/210787769-513b1655-c514-4f2e-beae-dcffcca11d2a.png)

6. Переходим в класс EmployeeService, прописываем необходимые импорты.
![image](https://user-images.githubusercontent.com/95271008/210787871-392119b4-f322-4e5e-a5f8-ca71687bda9f.png)

7. Создаем необходимые методы для работы с данными.
![image](https://user-images.githubusercontent.com/95271008/210787959-3dab946a-0903-42b4-93db-20ed3ed03108.png)

# подгрузка данных из csv
![image](https://user-images.githubusercontent.com/95271008/210788081-38eeee7a-840f-45d3-82f2-84227bc3f9f2.png)

# создание объекта на основе полученных данных
![image](https://user-images.githubusercontent.com/95271008/210788209-d9955aae-d6f3-4eda-b3d6-6c5781559de7.png)

# выгрузка данных в бд
![image](https://user-images.githubusercontent.com/95271008/210788308-b0f51cdb-3cdf-4fc5-9e99-2a99808ac18f.png)

# создание таблицы
![image](https://user-images.githubusercontent.com/95271008/210788392-cb204a8f-6d80-4e43-8e2c-31c475e1aace.png)

8. Для работы с SQLite я использовал интерфейс Connection, который идеально подходил для данного мини-проекта.

9. В конце каждого метода не забываем закрывать statememnt.

10. В классе Main открываем единтственное соединение с БД и передаем его в качестве параметра конструктора в наш сервис.

11. Создаем небольшой интерфейс для пользовательского взаимодействия с кодом.

12. Так же, выходя за рамки задания, я написал метод, который выводит часть данных датасета. Это было необходимо для проверки корректности обработки данных.

13. В конце работы программы обязательно закрываем единственно открытое соединение.

# График

![image](https://user-images.githubusercontent.com/95271008/211306942-8a2f925c-2511-472b-a02a-55705a1a5165.png)

# Второе задание

![image](https://user-images.githubusercontent.com/95271008/211307004-0668d1a6-a8b0-4f20-9de4-31c58cc98960.png)

# Третье задание

![image](https://user-images.githubusercontent.com/95271008/211307049-d8ae5f96-0828-475c-8682-938b0ec0e357.png)




