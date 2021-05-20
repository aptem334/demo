# demo
docker hello world
запустить:
docker run -d -p 8080:8080 -t demo-simple:0.0.1
Опция -d означает старт процесса в фоновом режиме. 
Опция -p тоже важна — дело в том, что контейнер собирается в полностью изолированном окружении. 
Тот факт, что приложение внутри контейнера запущено на порту 8080, не означает, что оно доступно вне контейнера на этом порту.

Требуется явно указать, что порту 8080 в контейнере (здесь второе значение — это порт, на котором работает наше приложение в контейнере) соответствует порт 8080 на локальной машине, который будет использоваться при обращении к контейнеру.
Поэтому пишем через двоеточие -p 8080:8080.

для запуска БД :
docker run -d --name db -p 1521:1521 -p 5500:5500 -e ORACLE_PWD=sys -e ORACLE_EDITION=enterprise --mount source=OracleDBData,target=/opt/oracle/oradata \ container-registry.oracle.com/database/enterprise:19.3.0.0
