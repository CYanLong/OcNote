# OcNote
&emsp;&emsp;这是我学习编程以来做的第一个完整的Web网站项目。虽然很小，只是单页的增删改查，但在做的时候仍然学习到了很多东西，也认识到了一些自己的不足。

下面是项目的简单介绍:

项目名叫做OcNote,是一款网页端的 日程或笔记 记录软件，类似于印象笔记。

<img src="http://7xtdq2.com1.z0.glb.clouddn.com/ocnote_2.PNG">

<img src="http://7xtdq2.com1.z0.glb.clouddn.com/ocnote_1.PNG">

<img src="http://7xtdq2.com1.z0.glb.clouddn.com/ocnote_3.PNG">

首先，大概谈谈这个项目用到的技术吧。

&emsp;&emsp;1.持久层用的Hibernate.4.3.6.Final，数据库为mysql。

&emsp;&emsp;2.Web控制器使用的SpringMVC。

&emsp;&emsp;3.使用RESTful API理念。

&emsp;&emsp;4.使用Maven作为项目管理和构建工具。

&emsp;&emsp;5.使用了 com.fasterxml.jackson.core 第三方json工具包。


以下是项目现在存在的不足:

&emsp;&emsp;1.没有真正理解RESTful API，加上RESTful API的典型应用场景不适用于此项目，所以 RUI 的设计现在仍旧存在一些错误。

&emsp;&emsp;2.对数据库事务及异常处理的认识不足，没有实现事务异常处理。这也是此项目现在存在的最大的问题。


经验:这次项目最重要的是让我认识到了单元测试的重要性，在进行交互时，由于之前没有进行过单元测试，导致整个交互浪费了很长时间。

接下来，我将对此项目进行重构并添加一些新的功能:

&emsp;&emsp;1.持久层将替换为MyBatis，实现事务异常处理。

&emsp;&emsp;2.将添加自动登录(记住我)功能。

&emsp;&emsp;3.登录时添加验证码和邮箱真实认证功能。

&emsp;&emsp;4.Task的内容支持插入图片功能。

