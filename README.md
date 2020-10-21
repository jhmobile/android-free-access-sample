
# 移动端免登陆使用webView使用文档
- 文档维护人：田俊梅
- 当前最新版本：sdk-version=V0.5.2

#### 1.使用建议
- 开发环境支持gradle4.4的脚本
- targetSdkVersion >=26

#### 2.初始化
- 主项目build.gradle设置仓库地址

<pre>
<code>
allprojects {
   repositories {
      maven {
          url "http://mvn.jinhui365.cn:19909/repository/release-other/"
            }
       ......
        }
    }
</code>
</pre>
- app目录下面build.gradle 添加库引用
<pre>
<code>
  dependencies {
    ......
   implementation 'com.jinhui365.android:jinhui-sdk:sdk-version'
   }
</code>
</pre>
  
  
- 在application调用以下API进行初始化,钩子type值解释表；参考文档

<pre>
<code>
    @Override
    public void onCreate(){
        super.onCreate();
        ......
        
         Map<String, Object> params = new HashMap<>();
         params.put("themeColor", "#e2bea5");
         JHWebViewManager.getInstance()
                 .setAppKeySecret("jh2adf1307ec1830bc", "45addb98955e4359a9d7a695979fb27c")
                 .setEnvironmentModel(false)
                 .setParams(params)//可不传，根据需求传递相关扩展参数
                 .setJHWebViewListener(new JHWebViewConnectJSListener() {
                     @Override
                     public void onConnectListener(String type, Map<String, Object> options) {
                         switch (type) {
                             case "login":
                                 //调用登录页面
                                 LoginModuleActivity.startActivity(getBaseContext());
                                 break;
                             case "risk":
                                 //调用风险测评页面
                                 RiskModuleActivity.startActivity(getBaseContext());
                                 break;
                         }
                     }
                 })
                 .init(this);
                 
    }
</code>
</pre>

#### 2.模块加载页面使用JHWebFragment(底部含有tab等组件)
- 具体实现，可参考demo

#### 3. 获取到用户信息，需要同步给SDK
- 已登录用户，需要在application里面初始化之后，直接设置用户信息

<pre>
<code>
        Map<String, Object> thirdInfo = new HashMap<>();
        thirdInfo.put("name", "卫子夫");//姓名
        thirdInfo.put("idNo", "110000197603217303");//身份证号
        thirdInfo.put("mobile", "18515279796");//手机号
        thirdInfo.put("bankAccount", "10004695");//华创资金账户
        JHWebViewManager.getInstance().login(thirdInfo);
</code>
</pre>

#### 4.非模块页面，加载任意页面调用
<pre>
<code>
JHWebViewManager.getInstance().push(context,"url",params);
</code>
</pre>

#### 5.退出，调用如下API
<pre>
<code>
JHWebViewManager.getInstance().logout();
</code>
</pre>

#### 6.JHWebViewConnectJSListener通信类型对应的回调参数及方法
|key|枚举值|描述|回调方法|必填参数|
|:-:|:-:|:-:|:-:| :-:| 
|type|login|登录通信|JHWebViewManager.getInstance().login(thirdInfo)|详见login参数表|
|type|login|登录失败,取消登录|JHWebViewManager.getInstance().connectResult(-1, "message", "login", null)||
|type|risk|风险测评通信|JHWebViewManager.getInstance().connectResult(code, "message", "type", params);|详见risk参数表,（该参数废弃）|

#### 7.type类型参数表
##### （1）login参数表
|参数key|是否必填|描述|
|:-:|:-:|:-:|
|name|是|姓名|
|idNo|是|身份证号|
|mobile|是|手机号|
|bankAccount|是|资金账号|
|...|否|其他扩展参数，暂时不需要|

##### （2）risk参数表
|参数key|是否必填|描述|
|:-:|:-:|:-:|
|code|是|状态：0 成功；1:失败|
|message|否|状态描述|
|type|是|处理的通信类型：risk|
|params|否|通信返回参数；可为空|


#### 8.setParams() 方法参数表
|参数key|是否必填|描述|示例|
|:-:|:-:|:-:|:-:|
|themeColor|否|主题色纸|#cccccc|
|statusBarColor|否|导航栏颜色，不设置默认和主题色相同|#cccccc|
|......|否|未来扩展参数|暂无|

#### 9.getJHWebActivityInstance() 获取webView的Activity对象
 - 不建议使用，必须使用，记得维护activity的销毁，防止出现内存泄漏


#### 其他

- 加载的url，支持绝对地址和相对地址：
   - 相对地址：首页  /
   - 据对地址：首页   https://m.jinhui365.cn/?
   - url可为空，为空默认进入首页
   
- 常规错误处理
  - gson包冲突：
  
  <pre>
  <code>
  
     implementation ('com.jinhui365.android:jinhui-sdk:sdk-version'){
             exclude group : 'com.google.code.gson'
         }
         
  </code>
  </pre>
  
 - 混淆
  - 需要在混淆文件内添加如下代码：
  
   <pre>
     <code>
     
      -keep class com.jinhui365.**{*;}
      -keep interface com.jinhui365.**{*;}
      -keepclasseswithmembers class com.jinhui365.util.webview.JHWebChromeClient{
          public *;
      }
      -keepclasseswithmembers class com.jinhui365.util.webview.WebViewClient{
          public *;
      }
      
     </code>
   </pre>

- 加载以下页面需要参数及示例
   - 参考示例
    <pre>
     <code>
      JHWebViewManager.getInstance().push(MainActivity.this, "/channel/product/huoqi");
    </code>
   </pre> 
   - 参数列表

    |页面|参数|扩展参数|备注|
    |:-:|:-:|:-:|:-:|
    |活期理财|/channel/product/huoqi |无||
    |稳健理财|/channel/product/wenjian|无||
    |资管私募|/channel/product/ziguansimu|无||
    |公募基金|/product/mutual|无||
    |股交产品|/channel/product/gujiao|无||
  

