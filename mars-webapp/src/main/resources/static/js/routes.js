var routes = [
  // Index page
  {
    path: '/',
    url: './index.html',
    name: 'home',
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

    //常见问题
    {
        path: '/message/',
        url: './pages/message.html',
        name: 'message',
    },
  // Default route (404 page). MUST BE THE LAST
  {
    path: '(.*)',
    url: './pages/404.html',
  },
];
