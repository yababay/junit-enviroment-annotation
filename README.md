# Универсальная аннотация для JUnit-тестирования

## Применения:

Можно указывать: 

* наличие proxy;
* удаленный хост Selenoid;
* тип браузера

```java
@Env(withProxy = true, remote = "http://localhost:4444/wd/hub")
...
@Env(withProxy = false)
...
@Env(browser = Env.Browser.FIREFOX)
...
@Env // сброс всех параметров к значениям по умолчанию: 
     // т.е. browser = Env.Browser.CHROME, и, соответственно, Configuration.browser = "chrome"
     // remote = "" и, соответственно, Configuration.remote = null 
     // withProxy = false и, соответственно, Configuration.proxyEnabled = false
```

## Методы и классы

Аннотация работает как на уровне методов, так и на уровне классов, причем их можно комбинировать.
Предпочтительный способ - писать аннотацию над классом, в котором сгруппировать методы с одинаковыми настройками. 

Для корректной работы с методами нужно обязательно указать над классом теста:

```java
@Execution(ExecutionMode.SAME_THREAD)
```

Кроме того, если аннотация используется с методами, над каждым из них нужно писать 

```java
@Env
```
чтобы сбросить параметры. Они почему-то передаются в непредсказуемом порядке, если этого не сделать. 
В будущих версиях постараемся устранить эту проблему.
