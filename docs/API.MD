# API Description
## Overview

|  URI| Method| Need Session| Description|
|------|:--------|:--------------|:-----------|
| [account/signin](#signin)| POST| NO| 登录|
| [account/signup](#signup)| POST| NO|注册|
| [account/signout](#signout)| GET| YES| 登出|
| [account/password/retrieve](#retrieve-password)| POST| NO| 找回密码|
| [account/password/reset](#reset-password)| POST| NO| 重置密码|
| [account/password/change](#change-password)| POST| YES| 修改密码|
| [code/send](#send-verify-code)| POST| NO| 发送验证码 |
| [code/verify](#verify-code)| POST| NO| 验证验证码|
|   |   |   |    |
| [user/profile](#get-user-profile-detail)| GET| YES| 获取用户基本信息|
| [user/profile](#update-user-profile)| POST| YES| 修改用户基本信息|
| [user/{userId}/profile](#view-user-profile)| GET| NO| 查看用户信息|
| [educationResumes](#list-education-resume)| GET| YES| 获取教育经历列表|
| [educationResume](#update-education-resume)| GET| YES| 更新教育经历|
| [workResumes](#list-work-resume)| GET| YES| 获取工作经历列表|
| [workResume](#update-work-resume)| GET| YES| 更新工作经历|
|   |   |   |    | 
| serverTime| GET| NO| 获取系统时间|
| [image](#upload-image)| POST| YES| 上传图片|
| [appVersion](#get-app-version)| GET| NO| 获取app版本信息|
| [feedback](#feedback)| POST| YES| 意见反馈|
| [locations/{parentId}](#list-locations)| GET| NO| 获取地区列表|
| [industryDomains](#list-industry-domains)| GET| NO| 获取行业领域列表|
| [startupRoles](#list-startup-roles)| GET| NO| 获取创业角色列表|
| [startupStatus](#list-startup-status)| GET| NO| 获取创业状态列表|
| [projectPhases](#list-project-phases)| GET| NO| 获取项目阶段列表|
|   |   |   |    |
| [demands](#list-demand)| GET| NO| 获取需求列表|
| [user/{userId}/demands](#list-user-demand)| POST| NO| 获取用户创建的需求列表|
| [demand/{demandId}](#get-demand-detail)| GET| NO| 获取需求详情|
| [demand](#create-demand)| POST| YES| 创建需求|
| [demand/{demandId}](#delete-demand)| DELETE| YES| 删除需求|
| [demand/{demandId}/like](#like-demand)| POST| YES| 关注需求|
| [demand/{demandId}/like](#unlike-demand)| DELETE| YES| 取消关注需求|
| [demand/{demandId}/comment](#comment-demand)| POST| YES| 评论需求|
|   |   |   |    |
| [projects](#list-project)| GET| NO| 获取项目列表|
| [user/{userId}/projects](#list-user-project)| POST| NO| 获取用户创建的项目列表|
| [project](#create-project)| POST| YES| 创建项目|
| [project/{projectId}/like](#like-project)| POST| YES| 关注项目|
| [project/{projectId}/like](#unlike-project)| DELETE| YES| 取消关注项目|
|   |   |   |    |
| [user/friend/{friendId}](#add-friend)| POST| YES| 添加好友请求|
| [user/friend/{friendId}/accept](#accept-friend)| POST| YES| 接受好友请求|
| [user/friends](#list-friends)| GET| YES|获取好友列表|
| [user/newFriends](#list-new-friends)| GET| YES| 获取好友请求列表|


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
            "educationResumes": [
                {
                    "name": "上海交通大学",
                    "major": "计算机专业"
                },
                {
                    "name": "清华大学",
                    "major": "计算机专业"
                }
            ],
            "email": "",
            "fullDomains": "互联网  移动互联网  电子商务",
            "fullLocation": "北京|北京",
            "gender": "F",
            "introduction": "HAHAHAHAHA",
            "isEmailVerified": false,
            "isMobileVerified": true,
            "imId": 1,
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
            "userId": 1,
            "workResumes": [
                {
                    "name": "百度",
                    "major": "高级开发工程师"
                }
            ]
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
| 403  | 无权访问，请重试|


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

#### View User Profile

 通过用户ID获取用户信息。

- **Request URL**
    `http://API_DOMAIN/user/{userId}/profile`

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
            "educationResumes": [
                {
                    "name": "上海交通大学",
                    "major": "计算机专业"
                },
                {
                    "name": "清华大学",
                    "major": "计算机专业"
                }
            ],
            "email": "",
            "fullDomains": "互联网  移动互联网  电子商务",
            "fullLocation": "北京|北京",
            "gender": "F",
            "introduction": "HAHAHAHAHA",
            "isEmailVerified": false,
            "isMobileVerified": true,
            "imId": 1,
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
            "userId": 1,
            "workResumes": [
                {
                    "name": "百度",
                    "major": "高级开发工程师"
                }
            ]
        },
        "status": "SUCCEED"
    }
    ```

- **Error**
    ```
    {
        "body": {
            "error": {
                "code": 20300,
                "msg": "用户不存在"
            }
        },
        "status": "FAILED"
    }
    ```

| Code    |   Description   |
|:--------|:----------------|
| 20300  | 用户不存在|

#### List Education Resume

获取教育经历列表.

- **Request URL**
    `http://API_DOMAIN/educationResumes`

- **Request Method**
    GET

- **Request Header**

| Name | Description | 
| :-------- | :-------- | 
| Cookie	| Add the cookie, it's value are from the response of  Sigh in api. The value is like "JSESSIONID=*" |

- **Request Parameters**

    N/A

- **Response**
    ```
    {
        "body": [
          {
            "name": "上海交通大学",
            "major": "计算机专业"
          },
          {
            "name": "清华大学",
            "major": "计算机专业"
          }
        ],
        "status": "SUCCEED"
    }
    ```

- **Error**

    N/A

#### Update Education Resume

 更新教育经历.

- **Request URL**
    `http://API_DOMAIN/educationResume`

- **Request Method**
    POST

- **Request Header**

| Name | Description | 
| :-------- | :-------- | 
| Cookie	| Add the cookie, it's value are from the response of  Sigh in api. The value is like "JSESSIONID=*" |


- **Request Parameters**

|  Name| Requires| Type and Range| Description|
|------|:--------|:--------------|:-----------|
|resumes   |required |string |JSON字符串，[{"name":"上海交通大学","major":"计算机专业"},{"name":"清华大学","major":"计算机专业"}]|

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
| 10000 | 参数错误|

#### List Work Resume

获取工作经历列表.

- **Request URL**
    `http://API_DOMAIN/workResumes`

- **Request Method**
    GET

- **Request Header**

| Name | Description | 
| :-------- | :-------- | 
| Cookie	| Add the cookie, it's value are from the response of  Sigh in api. The value is like "JSESSIONID=*" |

- **Request Parameters**

    N/A

- **Response**
    ```
    {
        "body": [
          {
            "name": "百度",
            "major": "高级工程师"
          }
        ],
        "status": "SUCCEED"
    }
    ```

- **Error**

    N/A

#### Update Work Resume

 更新工作经历.

- **Request URL**
    `http://API_DOMAIN/workResume`

- **Request Method**
    POST

- **Request Header**

| Name | Description | 
| :-------- | :-------- | 
| Cookie	| Add the cookie, it's value are from the response of  Sigh in api. The value is like "JSESSIONID=*" |


- **Request Parameters**

|  Name| Requires| Type and Range| Description|
|------|:--------|:--------------|:-----------|
|resumes   |required |string |JSON字符串，[{"name":"百度","major":"软件工程师"}]|

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
| 10000 | 参数错误|


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

#### Feedback

意见反馈.

- **Request URL**
    `http://API_DOMAIN/feedback`

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
| text | required | string | 意见内容 |

- **Response**

    ```
    {"body":null,"status":"SUCCEED"}
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

#### List Startup Roles

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

#### List Project Phases

获取项目阶段列表.

- **Request URL**
    `http://API_DOMAIN/projectPhases`

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
                "name": "原型构建阶段"
            },
            {
                "id": 2,
                "name": "Beta测试阶段"
            },
            {
                "id": 3,
                "name": "已运营"
            },
            {
                "id": 4,
                "name": "已收益"
            }
        ],
        "status": "SUCCEED"
    }
    ```

- **Error**

    N/A


### Demand API

#### List Demand

获取需求列表.

- **Request URL**
    `http://API_DOMAIN/demands`

- **Request Method**
    GET

- **Request Header**
    N/A

- **Request Parameters**

|  Name| Requires| Type and Range| Description|
|------|:--------|:--------------|:-----------|
| type  | optional | int | 根据type过滤 |
| offset| optional | int | 每次获取的起始点，limit * 当前页码 或者 limit * (下一页码 - 1)， 默认值是0 |
| limit | optional | int | 每次获取的数量，默认值是10|

- **Response**
    ```
    {
        "body": {
            "count": 1,
            "list": [
                {
                    "commentCount": 0,
                    "comments": [],
                    "content": "123456789",
                    "created": "2015-05-30 12:53:59",
                    "likeCount": 0,
                    "likers": [],
                    "status": "active",
                    "type": 1,
                    "user": {
                        "avatar": "http://120.24.228.100:8080/copartner/resources/images/default_avatar.png",
                        "location": "上海|上海",
                        "name": "15821512816",
                        "role": "产品",
                        "userId": 2
                    }
                }
            ]
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

#### List User Demand

获取用户创建的需求列表.

- **Request URL**
    `http://API_DOMAIN/user/{userId}/demands`

- **Request Method**
    GET

- **Request Header**
    N/A

- **Request Parameters**

|  Name| Requires| Type and Range| Description|
|------|:--------|:--------------|:-----------|
| type  | optional | int | 根据type过滤 |
| offset| optional | int | 每次获取的起始点，limit * 当前页码 或者 limit * (下一页码 - 1)， 默认值是0 |
| limit | optional | int | 每次获取的数量，默认值是10|

- **Response**
    ```
    {
        "body": {
            "count": 1,
            "list": [
                {
                    "commentCount": 0,
                    "comments": [],
                    "content": "123456789",
                    "created": "2015-05-30 12:53:59",
                    "likeCount": 0,
                    "likers": [],
                    "status": "active",
                    "type": 1,
                    "user": {
                        "avatar": "http://120.24.228.100:8080/copartner/resources/images/default_avatar.png",
                        "location": "上海|上海",
                        "name": "15821512816",
                        "role": "产品",
                        "userId": 2
                    }
                }
            ]
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

#### Get Demand Detail

获取需求详情.

- **Request URL**
    `http://API_DOMAIN/demand/{demandId}`

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
            "commentCount": 0,
            "content": "123456789",
            "created": "2015-05-30 12:53:59",
            "likeCount": 0,
            "likers": [],
            "project": null,
            "status": "active",
            "type": 1,
            "user": {
                "avatar": "",
                "location": "上海|上海",
                "name": "15821512816",
                "role": "产品",
                "userId": 2
            }
        },
        "status": "SUCCEED"
    }
    ```

- **Error**
    ```
    {
        "body": {
            "error": {
                "code": 30000,
                "msg": "该需求不存在"
            }
        },
        "status": "FAILED"
    }
    ```

| Code    |   Description   |
|:--------|:----------------|
| 30000 | 该需求不存在 |

#### Create Demand

 创建需求.

- **Request URL**
    `http://API_DOMAIN/demand`

- **Request Method**
    POST

- **Request Header**

| Name | Description | 
| :-------- | :-------- | 
| Cookie	| Add the cookie, it's value are from the response of  Sigh in api. The value is like "JSESSIONID=*" |


- **Request Parameters**

|  Name| Requires| Type and Range| Description|
|------|:--------|:--------------|:-----------|
|content   |required |string |内容，<=200字|
|type      |required |int |需求分类，1.创意交流 2.寻求搭档 3.寻求投资 |
|projectId |optional |int |所关联的项目 |

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
| 10000 | 参数错误|
| 30100 | 该项目不存在|
| 30101 | 无权操作该项目|

#### Delete Demand

 删除需求.

- **Request URL**
    `http://API_DOMAIN/demand/{demandId}`

- **Request Method**
    DELETE

- **Request Header**

| Name | Description | 
| :-------- | :-------- | 
| Cookie	| Add the cookie, it's value are from the response of  Sigh in api. The value is like "JSESSIONID=*" |


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
    ```
    {
        "body": {
            "error": {
                "code": 30000,
                "msg": "该需求不存在"
            }
        },
        "status": "FAILED"
    }
    ```

| Code    |   Description   |
|:--------|:----------------|
| 30000 | 该需求不存在|
| 30001 | 无权操作该需求|

#### Like Demand

 关注需求.

- **Request URL**
    `http://API_DOMAIN/demand/{demandId}/like`

- **Request Method**
    POST

- **Request Header**

| Name | Description | 
| :-------- | :-------- | 
| Cookie	| Add the cookie, it's value are from the response of  Sigh in api. The value is like "JSESSIONID=*" |


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
    ```
    {
        "body": {
            "error": {
                "code": 30000,
                "msg": "该需求不存在"
            }
        },
        "status": "FAILED"
    }
    ```

| Code    |   Description   |
|:--------|:----------------|
| 30000 | 该需求不存在|

#### unlike Demand

 取消关注需求.

- **Request URL**
    `http://API_DOMAIN/demand/{demandId}/like`

- **Request Method**
    DELETE

- **Request Header**

| Name | Description | 
| :-------- | :-------- | 
| Cookie	| Add the cookie, it's value are from the response of  Sigh in api. The value is like "JSESSIONID=*" |


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
    ```
    {
        "body": {
            "error": {
                "code": 30000,
                "msg": "该需求不存在"
            }
        },
        "status": "FAILED"
    }
    ```

| Code    |   Description   |
|:--------|:----------------|
| 30000 | 该需求不存在|

#### Comment Demand

 评论需求.

- **Request URL**
    `http://API_DOMAIN/demand/{demandId}/comment`

- **Request Method**
    POST

- **Request Header**

| Name | Description | 
| :-------- | :-------- | 
| Cookie	| Add the cookie, it's value are from the response of  Sigh in api. The value is like "JSESSIONID=*" |


- **Request Parameters**

|  Name| Requires| Type and Range| Description|
|------|:--------|:--------------|:-----------|
|demandId  |required |int |要评论的需求ID |
|content   |required |string |评论内容|
|parentId  |optional |int |父级评论ID，用作评论的评论 |

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
| 10000 | 参数错误|
| 30000 | 该需求不存在|


### Project API

#### List Project

获取项目列表.

- **Request URL**
    `http://API_DOMAIN/projects`

- **Request Method**
    GET

- **Request Header**
    N/A

- **Request Parameters**

|  Name| Requires| Type and Range| Description|
|------|:--------|:--------------|:-----------|
| offset| optional | int | 每次获取的起始点，limit * 当前页码 或者 limit * (下一页码 - 1)， 默认值是0 |
| limit | optional | int | 每次获取的数量，默认值是10|

- **Response**
    ```
    {
        "body": {
            "count": 2,
            "list": [
                {
                    "commentCount": 0,
                    "content": "这个项目很好",
                    "industryDomain": "互联网",
                    "likeCount": 1,
                    "location": "上海|上海",
                    "logo": "http://h.hiphotos.baidu.com/imgad/pic/item/77094b36acaf2edd9cc64f4c881001e93901935b.jpg",
                    "name": "项目1",
                    "projectPhase": "原型构建阶段"
                },
                {
                    "commentCount": 0,
                    "content": "哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈",
                    "industryDomain": "互联网",
                    "likeCount": 0,
                    "location": "上海|上海",
                    "logo": "http://h.hiphotos.baidu.com/imgad/pic/item/77094b36acaf2edd9cc64f4c881001e93901935b.jpg",
                    "name": "NB的项目",
                    "projectPhase": "原型构建阶段"
                }
            ]
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

#### List User Project

获取用户创建的项目列表.

- **Request URL**
    `http://API_DOMAIN/user/{userId}/projects`

- **Request Method**
    GET

- **Request Header**
    N/A

- **Request Parameters**

|  Name| Requires| Type and Range| Description|
|------|:--------|:--------------|:-----------|
| offset| optional | int | 每次获取的起始点，limit * 当前页码 或者 limit * (下一页码 - 1)， 默认值是0 |
| limit | optional | int | 每次获取的数量，默认值是10|

- **Response**
    ```
    {
        "body": {
            "count": 1,
            "list": [
                {
                    "commentCount": 0,
                    "content": "这个项目很好",
                    "industryDomain": "互联网",
                    "likeCount": 1,
                    "location": "上海|上海",
                    "logo": "http://h.hiphotos.baidu.com/imgad/pic/item/77094b36acaf2edd9cc64f4c881001e93901935b.jpg",
                    "name": "项目1",
                    "projectPhase": "原型构建阶段"
                }
            ]
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


#### Create Project

 创建项目.

- **Request URL**
    `http://API_DOMAIN/project`

- **Request Method**
    POST

- **Request Header**

| Name | Description | 
| :-------- | :-------- | 
| Cookie	| Add the cookie, it's value are from the response of  Sigh in api. The value is like "JSESSIONID=*" |


- **Request Parameters**

|  Name| Requires| Type and Range| Description|
|------|:--------|:--------------|:-----------|
|name   |required |string |项目名称，<=50字|
|content|required |string |项目内容，<=200字|
|logo   |optional |string |项目LOGO|
|projectPhaseId  |required |long |项目阶段|
|industryDomainId|required |long |项目所属领域|
|locationId |required |long |项目所在地区|
|initDate   |required |string |项目启动时间， 格式yyyy-MM|

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
| 10000 | 参数错误|
| 10200 | 地区不存在|
| 10230 | 行业领域不存在|
| 10240 | 项目阶段不存在|

#### Like Project

 关注项目.

- **Request URL**
    `http://API_DOMAIN/project/{projectId}/like`

- **Request Method**
    POST

- **Request Header**

| Name | Description | 
| :-------- | :-------- | 
| Cookie	| Add the cookie, it's value are from the response of  Sigh in api. The value is like "JSESSIONID=*" |


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
    ```
    {
        "body": {
            "error": {
                "code": 30100,
                "msg": "该项目不存在"
            }
        },
        "status": "FAILED"
    }
    ```

| Code    |   Description   |
|:--------|:----------------|
| 30100 | 该项目不存在|

#### unlike Project

 取消关注项目.

- **Request URL**
    `http://API_DOMAIN/project/{projectId}/like`

- **Request Method**
    DELETE

- **Request Header**

| Name | Description | 
| :-------- | :-------- | 
| Cookie	| Add the cookie, it's value are from the response of  Sigh in api. The value is like "JSESSIONID=*" |


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
    ```
    {
        "body": {
            "error": {
                "code": 30100,
                "msg": "该项目不存在"
            }
        },
        "status": "FAILED"
    }
    ```

| Code    |   Description   |
|:--------|:----------------|
| 30100 | 该项目不存在|


#### Add Friend

 添加好友请求。

- **Request URL**
    `http://API_DOMAIN/user/friend/{friendId}`

- **Request Method**
    POST

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
    ```
    {
        "body": {
            "error": {
                "code": 20300,
                "msg": "用户不存在"
            }
        },
        "status": "FAILED"
    }
    ```

| Code    |   Description   |
|:--------|:----------------|
| 403   | 无权访问，请重试 |
| 20300 | 用户不存在 |


#### Accept Friend

 接受好友请求。

- **Request URL**
    `http://API_DOMAIN/user/friend/{friendId}/accept`

- **Request Method**
    POST

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
            "friendId": 2,
            "name": "test2",
            "avatar": "http://120.24.228.100:8080/copartner/resources/images/default_avatar.png",
            "imId": 2
        },
        "status": "SUCCEED"
    }
    ```

- **Error**
    ```
    {
        "body": {
            "error": {
                "code": 20300,
                "msg": "用户不存在"
            }
        },
        "status": "FAILED"
    }
    ```

| Code    |   Description   |
|:--------|:----------------|
| 403   | 无权访问，请重试 |
| 20300 | 用户不存在 |

#### List Friends
 获取好友列表。

- **Request URL**
    `http://API_DOMAIN/user/friends`

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
        "body": [
            {
                "friendId": 2,
                "name": "test2",
                "avatar": "http://120.24.228.100:8080/copartner/resources/images/default_avatar.png",
                "imId": 2
            }
        ],
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
| 403  | 无权访问，请重试|

#### List New Friends
 获取好友请求列表。

- **Request URL**
    `http://API_DOMAIN/user/newFriends`

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
        "body": [
            {
                "friendId": 2,
                "name": "test2",
                "avatar": "http://120.24.228.100:8080/copartner/resources/images/default_avatar.png",
                "imId": 2
            }
        ],
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
| 403  | 无权访问，请重试|
