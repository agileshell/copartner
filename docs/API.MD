# API Description
## Overview

|  URI| method| need session |description| 
| ----| :-------| :------------| 
| [account/signin](#signin)| POST| NO| 登录| 
| [account/signup](#signup)| POST| NO| 注册| 
| [account/signout](#signout)| GET| YES| 登出| 
| [account/password/retrieve](#retrieve-password)| POST| NO| 找回密码|
| [account/password/reset](#reset-password)| POST| NO| 重置密码|
| [account/password/change](#change-password)| POST| YES| 修改密码| 
| [code/send](#send-verify-code)| POST| NO| 发送验证码 |
| [code/verify](#verify-code)| POST| NO| 验证验证码| 
|   |   |   |    | 
| [user/profile](#get-user-profile_detail)| GET| YES| 获取用户基本信息| 
| [user/profile](#update-user-profile)| POST| YES| 修改用户基本信息|
|   |   |   |    | 
| serverTime| GET| NO| 获取系统时间| 
| [image](#upload-image)| POST| YES| 上传图片|
| [appVersion](#get-app-version)| GET| NO| 获取app版本信息| 
| [locations/{parentId}](#list-locations)| GET| NO| 获取地区列表| 
| [industryDomains](#list-industry-domains)| GET| NO| 获取行业领域列表| 
| [startupRoles](#list-startup-roles)| GET| NO| 获取创业角色列表| 
| [startupStatus](#list-startup-status)| GET| NO| 获取创业状态列表|

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


### User API

#### Get user profile detail

 获取用户的基本信息。

- **Request URL**
    `http://API_DOMAIN/user/profile`

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
        "body": {
            "age": "25-30",
            "avatar": "http://www.baidu.com/img/bd_logo1.png",
            "domains": [
                {
                    "id": 2,
                    "name": "移动互联网"
                },
                {
                    "id": 3,
                    "name": "电子商务"
                },
                {
                    "id": 1,
                    "name": "互联网"
                }
            ],
            "email": "",
            "fullDomains": "互联网  移动互联网  电子商务",
            "fullLocation": "北京|北京",
            "gender": "F",
            "introduction": "HAHAHAHAHA",
            "isEmailVerified": false,
            "isMobileVerified": true,
            "location": {
                "latitude": 0,
                "locationId": 35,
                "longitude": 0,
                "name": "北京",
                "parentId": 1,
                "pinyin": "beijing"
            },
            "mobile": "15812345678",
            "name": "GOGO",
            "startupRole": {
                "id": 1,
                "name": "产品"
            },
            "startupStatus": {
                "id": 1,
                "name": "有创业意愿"
            },
            "status": "active",
            "userId": 1
        },
        "status": "SUCCEED"
    }
    ```

- **Error**
    ```
    {
        "body": {
            "error": {
                "code": 403,
                "msg": "Forbidden"
            }
        },
        "status": "FAILED"
    }
    ```

| Code    |   Description   |
|:--------|:----------------|
| 403  | Forbidden.|


#### Update user profile

修改用户资料.

- **Request URL**
    `http://API_DOMAIN/user/profile`

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
|name     |optional |string(2-64) |昵称|
|gender   |optional |string |性别， M(男)或者F(女)|
|age      |optional |string |年龄，<25,25-30,31-35,36-40,41-50,>50|
|avatar   |optional |string |头像的路径|
|introduction|optional |string(<=100) |简介|
|locationId  |optional |Int |地区|
|domainIds   |optional |Int[] |关注领域，数组，例如1,2,3，或者传多个此参数|
|startupStatusId |optional |Int |创业状态|
|startupRoleId   |optional |Int |创业角色|

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
                "code": 10000,
                "msg": "参数错误"
            }
        },
        "status": "FAILED"
    }
    ```

| Code    |   Description   |
|:--------|:----------------|
| 403   | 无权访问，请重试 |
| 10000 | 参数错误 |
| 10200 | 地区不存在 |
| 10210 | 创业状态不存在 |
| 10220 | 个人角色不存在 |


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

#### Get App Version

获取app版本信息.

- **Request URL**
    `http://API_DOMAIN/appVersion`

- **Request Method**
    GET

- **Request Header**

    N/A

- **Request Parameters**

    N/A

- **Response**

    ```
    {
        "body": {
            "android_version": "1.0.1",
            "ios_version": "1.0.1",
            "ios_downloadURL": "http://7xjbd9.com1.z0.glb.clouddn.com/chuangkehui1.0.1.ipa",
            "android_downloadURL": "http://7xjbd9.com1.z0.glb.clouddn.com/chuangkehui1.0.1.apk"
        },
        "status": "SUCCEED"
    }
    ```

- **Error**

    N/A
    
#### List Locations

根据location父级ID获取子地区，如果父级ID为0则返回父级location.

- **Request URL**
    `http://API_DOMAIN/locations/{parentId}`

- **Request Method**
    GET

- **Request Header**

    N/A

- **Request Parameters**

    N/A

- **Response**

    ```
    {
        "body": [
            {
                "latitude": 0,
                "locationId": 35,
                "longitude": 0,
                "name": "北京",
                "parentId": 1,
                "pinyin": "beijing"
            }
        ],
        "status": "SUCCEED"
    }
    ```

- **Error**

    N/A

#### List Industry Domains

获取行业领域列表.

- **Request URL**
    `http://API_DOMAIN/industryDomains`

- **Request Method**
    GET

- **Request Header**

    N/A

- **Request Parameters**

    N/A

- **Response**

    ```
    {
        "body": [
            {
                "id": 1,
                "name": "互联网"
            },
            {
                "id": 2,
                "name": "移动互联网"
            },
            {
                "id": 3,
                "name": "电子商务"
            },
            {
                "id": 4,
                "name": "社交"
            },
            {
                "id": 5,
                "name": "游戏"
            },
            {
                "id": 6,
                "name": "云计算"
            },
            {
                "id": 7,
                "name": "IT服务"
            },
            {
                "id": 8,
                "name": "软件/工具"
            },
            {
                "id": 9,
                "name": "软硬件"
            },
            {
                "id": 10,
                "name": "数码电子"
            },
            {
                "id": 11,
                "name": "其它"
            }
        ],
        "status": "SUCCEED"
    }
    ```

- **Error**

    N/A
    
#### Get Startup Roles

获取创业角色列表.

- **Request URL**
    `http://API_DOMAIN/startupRoles`

- **Request Method**
    GET

- **Request Header**

    N/A

- **Request Parameters**

    N/A

- **Response**

    ```
    {
        "body": [
            {
                "id": 1,
                "name": "产品"
            },
            {
                "id": 2,
                "name": "技术"
            },
            {
                "id": 3,
                "name": "运营"
            },
            {
                "id": 4,
                "name": "营销"
            },
            {
                "id": 5,
                "name": "设计"
            },
            {
                "id": 6,
                "name": "其它"
            }
        ],
        "status": "SUCCEED"
    }
    ```

- **Error**

    N/A

#### List Startup Status

获取创业状态列表.

- **Request URL**
    `http://API_DOMAIN/startupStatus`

- **Request Method**
    GET

- **Request Header**

    N/A

- **Request Parameters**

    N/A

- **Response**

    ```
    {
        "body": [
            {
                "id": 1,
                "name": "有创业意愿"
            },
            {
                "id": 2,
                "name": "有创业想法"
            },
            {
                "id": 3,
                "name": "全职创业中"
            },
            {
                "id": 4,
                "name": "兼职创业中"
            },
            {
                "id": 5,
                "name": "投资人"
            },
            {
                "id": 6,
                "name": "媒体人"
            }
        ],
        "status": "SUCCEED"
    }
    ```

- **Error**

    N/A