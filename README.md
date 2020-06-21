
This Project is to learn Web automation using Selenium webdriver with Java.

I am following the below udemy course

```https://www.udemy.com/course/selenium-real-time-examplesinterview-questions/```

All the CSS selector details are available at

```http://pragmatictestlabs.com/2020/02/09/mastering-css-for-selenium-test-automation-2/```

##### Different ways to locate element
    
    id
    name
    class name
    linktext
    CSS selector
    xpath
  
1. Single attribute

```diff 
//input[@id='Email']'
```

2. Parent
//div[@class='form-group']//parent::div
3. Ancestor
//div[@class='form-group']//ancestor::div
4. Child
//div[@class='form-group']//child::input[1]
5. Descendant
//div[@class='form-group']//descendant::a
6. Following
//div[@class='form-group']//following::input
//div[@class='form-group']//following-sibling::div
7. Preceding
//div[@class='form-group']//preceding::input
//div[@class='form-group']//preceding-sibling::div

8. index based
//input[@class="desktop-input"][2]
//input[@class="desktop-input"][last()]
//input[@class="desktop-input"][last()-1]

9. Text value
//div[contains(text(),'CONTINUE')]
//div[starts-with(text(),'CONTINUE')]
//div[text()='CONTINUE']
//div[contains (.,'CONTINUE')]

10. And/Or
//input[@type='submit' or @name='btnReset']
//input[@type='submit' and @name='btnLogin']
 
 Starting Selenium grid in hub mode
 
 ``java -jar selenium-server-standalone-<version>.jar -role hub``
