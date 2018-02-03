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
    //入口
    {
        path: '/member_rk/',
        url: './pages/member_rk.html',
        name: 'member_rk',
    },
    //开通
    {
        path: '/member_kt/',
        url: './pages/member_kt.html',
        name: 'member_kt',
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
  // Default route (404 page). MUST BE THE LAST
  {
    path: '(.*)',
    url: './pages/404.html',
  },
];
