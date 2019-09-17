
# 移动端免登陆使用webView使用文档
- 文档维护人：田俊梅
- 当前最新版本：V0.1.5

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
          url "http://mvn.gemantic.com:19909/content/repositories/release-other/"
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
   implementation 'com.jinhui365.library:jinhui-lib:0.1.5'
   }
</code>
</pre>

- AndroidManifest.xml配置FileProvider
  - 具体配置，参考demo示例
  
  
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
|type|risk|风险测评通信|JHWebViewManager.getInstance().connectResult(code, "message", "type", params);|详见risk参数表]|

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
|......|否|未来扩展参数|暂无|


#### 其他

- 加载的url，支持绝对地址和相对地址：
   - 相对地址：首页  /
   - 据对地址：首页   https://m.jinhui365.cn/?
   - url可为空，为空默认进入首页
   
- 常规错误处理
  - gson包冲突：
  
  <pre>
  <code>
  
     implementation ('com.jinhui365.library:jinhui-lib:0.1.5'){
             exclude group : 'com.google.code.gson'
         }
         
  </code>
  </pre>

