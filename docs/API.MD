# API Description
## Overview
|  URI| method| need session |description| 
| ----| :-------| :------------| 
| account/signin| POST| NO| 登录| 
| account/signup| POST| NO| 注册| 
| account/signout| GET| YES| 登出| 
| account/password/retrieve| POST| NO| 找回密码|
| account/password/reset| POST| NO| 重置密码|
| account/password/change| POST| YES| 修改密码| 
| code/send| POST| NO| 发送验证码 |
| code/verify| POST| NO| 验证验证码| 
|   |   |   |    | 
| serverTime| GET| NO| 获取系统时间| 
| image| POST| YES| 上传图片|

## APIs

### Account API

#### Signin

 用户通过账户和密码登入系统。

- **Request URL**
    `http://API_DOMAIN/account/signin`

- **Request Method**
    POST

- **Request Header**
    N/A

- **Request Parameters**

|  Name| Requires| Type and Range| Description|
|------|:--------|:--------------|:-----------|
|account  |required |string |邮箱或者手机号码 |
|password |required |string |密码|

- **Response**

    ```
    {
        "body": null,
        "status": "SUCCEED"
    }
    ```
- **Response Header**

| Name | Description |
| :-------- | :-------- |
| Set-Cookies | the value contains "JSESSIONID=*" |

**Note:** 有些API需要用户登录才能操作，把 "JSESSIONID=*" 添加到需要登录才能访问的API的HEADER中.

- **Error**
    ```
    {
        "body": {
            "error": {
                "code": 20001,
                "msg": "账号或密码错误"
            }
        },
        "status": "FAILED"
    }
    ```

| Code    |   Description   |
|:--------|:----------------|
| 10000 | 参数错误 |
| 20001 | 账号或密码错误 |

#### Signup

 用户通过手机或者邮箱注册。如果是使用手机注册，首先要通过 code/send API 发送验证码；如果使用邮箱注册则不需要code，通过邮箱注册后会发送一封邮件，提醒用户去验证账户，如果用户不验证也不影响用户登录。

- **Request URL**
    `http://API_DOMAIN/account/signup`

- **Request Method**
    POST

- **Request Header**
    N/A

- **Request Parameters**

|  Name| Requires| Type and Range| Description|
|------|:--------|:--------------|:-----------|
|account  |required |string |邮箱或者手机号码 |
|password |required |string |密码|
|code     |optional |string |验证码，如果使用邮箱注册，不需要 |

- **Response**
    ```
    {
        "body": {
            "userId": 2
        },
        "status": "SUCCEED"
    }
    ```

- **Error**

    ```
    {
        "body": {
            "error": {
                "code": 10000,
                "msg": "参数错误"
            }
        },
        "status": "FAILED"
    }
    ```

| Code    |   Description   |
|:--------|:----------------|
|10000 |参数错误 |
|20003 |此电子邮件已被注册 |
|20004 |此手机号码已被注册 |
|20100 |验证码不正确 |

#### Signout

 登出。
- **Request URL**
    `http://API_DOMAIN/account/signout`

- **Request Method**
    GET

- **Request Header**

| Name | Description |
| :-------- | :-------- |
| Cookie    | Add the cookie, it's value are from the response of  Sigh in api. The value is like "JSESSIONID=*" |
**Note:** The Cookie is required.

- **Request Parameters**
    N/A

- **Response**
    ```
    {
        "body": null,
        "status": "SUCCEED"
    }
    ```

- **Error**
    N/A

#### Retrieve Password

 用户通过账号（手机号码或者邮箱）找回密码，此API只是发送验证码或者邮件，需要配合password/reset API 才能找回密码。

- **Request URL**
    `http://API_DOMAIN/account/password/retrieve`

- **Request Method**
    POST

- **Request Header**
    N/A

- **Request Parameters**

|  Name| Requires| Type and Range| Description|
|------|:--------|:--------------|:-----------|
|account  |required |string |邮箱或者手机号码 |

- **Response**

    ```
    {
        "body": null,
        "status": "SUCCEED"
    }
    ```

- **Error**
    ```
    {
        "body": {
            "error": {
                "code": 20000,
                "msg": "账号不存在"
            }
        },
        "status": "FAILED"
    }
    ```

| Code    |   Description   |
|:--------|:----------------|
| 10000 | 参数错误 |
| 20000 | 账号不存在 |
| 20002 | 账号不可用 |
| 20101 | 短信发送条数超过日上限 |
| 20102 | 短信发送失败 |

#### Reset Password

 用户在未登录的情况下重置密码。

- **Request URL**
    `http://API_DOMAIN/account/password/reset`

- **Request Method**
    POST

- **Request Header**
    N/A

- **Request Parameters**

|  Name| Requires| Type and Range| Description|
|------|:--------|:--------------|:-----------|
|account  |required |string |邮箱或者手机号码 |
|password |required |string |密码|
|code     |optional |string |验证码，如果使用邮箱重置密码，不需要 |

- **Response**

    ```
    {
        "body": null,
        "status": "SUCCEED"
    }
    ```

- **Error**
    ```
    {
        "body": {
            "error": {
                "code": 20000,
                "msg": "账号不存在"
            }
        },
        "status": "FAILED"
    }
    ```

| Code    |   Description   |
|:--------|:----------------|
| 10000 | 参数错误 |
| 20000 | 账号不存在 |
| 20002 | 账号不可用 |
| 20100 | 验证码不正确 |


#### Change Password

 用户在登录的情况下修改密码。

- **Request URL**
    `http://API_DOMAIN/account/password/change`

- **Request Method**
    POST

- **Request Header**

| Name | Description |
| :-------- | :-------- |
| Cookie    | Add the cookie, it's value are from the response of  Sigh in api. The value is like "JSESSIONID=*" |
**Note:** The Cookie is required.

- **Request Parameters**

|  Name| Requires| Type and Range| Description|
|------|:--------|:--------------|:-----------|
|oldPassword  |required |string |原始密码 |
|password     |required |string |新密码 |

- **Response**

    ```
    {
        "body": null,
        "status": "SUCCEED"
    }
    ```

- **Error**
    ```
    {
        "body": {
            "error": {
                "code": 20200,
                "msg": "旧密码不正确"
            }
        },
        "status": "FAILED"
    }
    ```

| Code    |   Description   |
|:--------|:----------------|
| 403   | 无权访问，请重试 |
| 10000 | 参数错误 |
| 20200 | 旧密码不正确 |


#### Send verify code

根据不同的type（register/retrievePwd）发送验证码。

- **Request URL**
    `http://API_DOMAIN/code/send`

- **Request Method**
    POST

- **Request Header**
    N/A

- **Request Parameters**

|  Name| Requires| Type and Range| Description|
|------|:--------|:--------------|:-----------|
|mobile |required |string |原始密码 |
|type   |required |string |类型， register（注册） 或者 retrievePwd（找回密码）|

- **Response**

    ```
    {
        "body": null,
        "status": "SUCCEED"
    }
    ```

- **Error**
    ```
    {
        "body": {
            "error": {
                "code": 20004,
                "msg": "Mobile is already registered"
            }
        },
        "status": "FAILED"
    }
    ```

| Code    |   Description   |
|:--------|:----------------|
| 10000 | 参数错误 |
| 20000 | 账号不存在 |
| 20002 | 账号不可用 |
| 20004 | 此手机号码已被注册 |
| 20101 | 短信发送条数超过日上限 |
| 20102 | 短信发送失败 |

#### Verify Code

 验证验证码是否正确，此API通常在用户输入验证码后触发。

- **Request URL**
    `http://API_DOMAIN/code/verify`

- **Request Method**
    POST

- **Request Header**
    N/A

- **Request Parameters**

|  Name| Requires| Type and Range| Description|
|------|:--------|:--------------|:-----------|
|mobile |required |string |原始密码 |
|type   |required |string |类型， register（注册） 或者 retrievePwd（找回密码）|
|code   |optional |string |验证码 |

- **Response**

    ```
    {
        "body": null,
        "status": "SUCCEED"
    }
    ```

- **Error**
    ```
    {
        "body": {
            "error": {
                "code": 20100,
                "msg": "验证码不正确"
            }
        },
        "status": "FAILED"
    }
    ```

| Code    |   Description   |
|:--------|:----------------|
| 10000 | 参数错误 |
| 20100 | 验证码不正确 |


### Util API

#### Upload image

上传图片.

- **Request URL**
    `http://API_DOMAIN/image`

- **Request Method**
    POST

- **Request Header**

| Name | Description | 
| :-------- | :-------- | 
| Cookie	| Add the cookie, it's value are from the response of  Sigh in api. The value is like "JSESSIONID=*" |

**Note:** The Cookie is required.

- **Request Parameters**

| Name | Requires | Type and Range | Description |
| :-------- | :-------- | :-------- | :-------- | 
| image | required | file | 图片文件 |

- **Response**

    ```
    {
        "body": {
            "image_url": "http://7xjbd9.com1.z0.glb.clouddn.com/7557003a-7055-459e-8164-803fa615d068.jpg",
            "image_path": "7557003a-7055-459e-8164-803fa615d068.jpg"
        },
        "status": "SUCCEED"
    }
    ```

- **Error**
    ```
    {
        "body": {
            "error": {
                "code": 10000,
                "msg": "参数错误"
            }
        },
        "status": "FAILED"
    }
    ```

| Code    |   Description   |
|:--------|:----------------|
| 10000 | 参数错误 |
| 10100 | 文件为空 |
| 10101 | 文件类型不正确 |
| 10102 | 文件过大 |
| 10103 | 文件缺少最小尺寸 |
| 10104 | 图片过宽 |
| 10105 | 图片过高 |
| 10106 | 图片缺少最小宽度 |
| 10107 | 图片缺少最小高度 |
| 10108 | 验证图片出错 |
| 10109 | 上传文件出错 |
