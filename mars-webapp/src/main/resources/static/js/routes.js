var routes = [
  // Index page
  {
    path: '/',
    url: './index.html',
    name: 'home',
  },
    //登录
    {
        path: '/login/',
        url: './pages/login.html',
        name: 'login',
    },
    //我的信息
    {
        path: '/me/',
        url: './pages/me.html',
        name: 'me',
    },
    //入口
    {
        path: '/member_rk/',
        url: './pages/member_rk.html',
        name: 'member_rk',
    },
    //开通
    {
        path: '/buyCard/',
        url: './pages/buyCard.html',
        name: 'buyCard',
    },
    //健康检查
    {
        path: '/heart_check/',
        url: './pages/heart_check.html',
        name: 'heart_check',
    },
    //社工学院
    {
        path: '/college/',
        url: './pages/college.html',
        name: 'college',
    },

    //常见问题
    {
        path: '/question/',
        url: './pages/question.html',
        name: 'question',
    },

    //消息
    {
        path: '/message/',
        url: './pages/message.html',
        name: 'message',
    },

    //爱补贴
    {
        path: '/lovebutie/',
        url: './pages/lovebutie.html',
        name: 'lovebutie',
    },

    //小卡包
    {
        path: '/xiaokabao/',
        url: './pages/xiaokabao.html',
        name: 'xiaokabao',
    },

    //健康卡
    {
        path: '/jiankangka/',
        url: './pages/jiankangka.html',
        name: 'jiankangka',
    },
    //保险服务
    {
        path: '/baoxianfuwu/',
        url: './pages/baoxianfuwu.html',
        name: 'baoxianfuwu',
    },
    //我的保单
    {
        path: '/wodebaodan/',
        url: './pages/wodebaodan.html',
        name: 'wodebaodan',
    },
    //保单详单
    {
        path: '/baoxianxiangdan/',
        url: './pages/baoxianxiangdan.html',
        name: 'baoxianxiangdan',
    },
    //添加银行卡
    {
        path: '/bankcard/',
        url: './pages/bankcard.html',
        name: 'bankcard',
    },

    //转帐
    {
        path: '/zhuanzhang/',
        url: './pages/zhuanzhang.html',
        name: 'zhuanzhang',
    },
    //转帐记录
    {
        path: '/zhuanzhangjilu/',
        url: './pages/zhuanzhangjilu.html',
        name: 'zhuanzhangjilu',
    },
    //转帐详情
    {
        path: '/zhuanzhangxiangqing/',
        url: './pages/zhuanzhangxiangqing.html',
        name: 'zhuanzhangxiangqing',
    },
    //补贴明细
    {
        path: '/butiemingxi/',
        url: './pages/butiemingxi.html',
        name: 'butiemingxi',
    },

  // Default route (404 page). MUST BE THE LAST
  {
    path: '(.*)',
    url: './pages/404.html',
  },
];
