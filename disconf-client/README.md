disconf-client
=======

分布式配置管理客户端模块

前提：
先去disconf服务器上面去上传自己项目的配置文件
>
1. 添加app，app名称对应自己项目
2. 添加配置文件，版本自定一般为1_0_0_0，然后选择输入方式，key值填 general.properties下面值填写自己所需要的配置（注：该key：general.properties 必须配置），配置的app名称和版本需和disconf配置文件中一致


对接三部曲：

a. 添加依赖
```
		<dependency>
			<groupId>com.xxx</groupId>
			<artifactId>config-center</artifactId>
			<version>0.0.1-SNAPSHOT</version>
		</dependency>
```
b. 添加disconf配置在根目录（resources目录下）
```
disconf.enable.remote.conf=true
disconf.conf_server_host=http://disconf.xxx.com
disconf.version=1_0_0_0
disconf.app=order_service
disconf.env=qa
disconf.ignore=
disconf.conf_server_url_retry_sleep_seconds=1
disconf.user_define_download_dir=./src/main/resources/config
disconf.app_conf_files_name=common.properties,datasource-user.properties,datasource-order.properties,mq.properties,redis-core.properties
```

c. 修改/添加AppConext扫描配置文件路径

```
package com.dianwoba.order.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource(locations = { "classpath:spring/*", "classpath*:spring/disconf.xml" })
public class AppContext {
}
```

Disconf配置详解 
所有配置均可以通过 命令行 -Dname=value 参数传入。
<table>
        <tr>
            <th>配置</th>
			<th>说明</th>
            <th>默认值</th>
            <th>是否必填</th>
        </tr>
        <tr>
            <td>disconf.conf_server_host</td>
			<td>配置服务器的 HOST,用逗号分隔 ，示例：127.0.0.1:8000,127.0.0.1:8000</td>
            <td></td>
            <td>是</td>
        </tr>
        <tr>
            <td>disconf.app</td>
			<td>APP 请采用 产品线_服务名 格式</td>
            <td>优先读取命令行参数，然后再读取此文件的值 </td>
            <td>否</td>
        </tr>
        <tr>
            <td>disconf.version</td>
			<td>版本号</td>
            <td>默认DEFAULT_VERSION。优先读取命令行参数，然后再读取此文件的值，最后才读取默认值。</td>
            <td>否</td>
        </tr>
        <tr>
            <td>disconf.enable.remote.conf</td>
			<td>是否使用远程配置文件，true(默认)会从远程获取配置， false则直接获取本地配置</td>
            <td>false</td>
            <td>否</td>
        </tr>        <tr>
            <td>disconf.env</td>
			<td>环境</td>
            <td>默认为 DEFAULT_ENV。优先读取命令行参数，然后再读取此文件的值，最后才读取默认值 </td>
            <td>否</td>
        </tr>        <tr>
            <td>disconf.ignore</td>
			<td>忽略的分布式配置，用空格分隔</td>
            <td>空</td>
            <td>否</td>
        </tr>
        <tr>
            <td>disconf.debug</td>
			<td>调试模式。调试模式下，ZK超时或断开连接后不会重新连接（常用于client单步debug）。非调试模式下，ZK超时或断开连接会自动重新连接。</td>
            <td>false</td>
            <td>否</td>
        </tr>
        <tr>
            <td>disconf.conf_server_url_retry_times</td>
			<td>获取远程配置 重试次数，默认是3次 </td>
            <td>3</td>
            <td>否</td>
        </tr>
        <tr>
            <td>disconf.conf_server_url_retry_sleep_seconds</td>
			<td>获取远程配置 重试时休眠时间，默认是5秒</td>
            <td>5</td>
            <td>否</td>
        </tr>
        <tr>
            <td>disconf.user_define_download_dir</td>
			<td>用户定义的下载文件夹, 远程文件下载后会放在这里。注意，此文件夹必须有有权限，否则无法下载到这里</td>
            <td>./disconf/download </td>
            <td>否</td>
        </tr>
        <tr>
            <td>disconf.enable_local_download_dir_in_class_path</td>
			<td>下载的文件会被迁移到classpath根路径下，强烈建议将此选项置为 true(默认是true)</td>
            <td>true</td>
            <td>否</td>
        </tr>
        <tr>
            <td>disconf.common_app</td>
			<td>通用配置app名称</td>
            <td>common</td>
            <td>否</td>
        </tr>
        <tr>
            <td>disconf.common_app_version</td>
			<td>通用配置app版本</td>
            <td>默认version。</td>
            <td>否</td>
        </tr>
        <tr>
            <td>disconf.common_app_env</td>
			<td>通用配置环境</td>
            <td>默认与app环境相同</td>
            <td>否</td>
        </tr>
        <tr>
            <td>disconf.app_conf_files_name</td>
			<td>需要的配置文件中间用,号隔开</td>
            <td>不填写的话，只会加载对应app下的specific.properties</td>
            <td>否</td>
        </tr>
</table>

