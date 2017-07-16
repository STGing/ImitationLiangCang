package com.example.pc.imitationliangcang.common;

/**
 * Created by PC on 2017/7/6.
 */

public class NetWorkUrl {

    //商店页面中：

    // 首页总API
    public static final String HOMEFRAGMENTURL = "http://mobile.iliangcang.com/goods/newShopHome?app_key=Android&sig=3780CB0808528F7CE99081D295EE8C0F%7C116941220826768&uid=626138098&user_token=0516ed9429352c8e1e3bd11c63ba6f54&v=1.0";

    // 分类总API
    public static final String CLASSIFYFRAGMENTURL = "http://mobile.iliangcang.com/goods/goodsCategory?app_key=Android&sig=430BD99E6C913B8B8C3ED109737ECF15%7C830952120106768&v=1.0";

    //品牌总API
    public static final String BRANDFRAGMENTURL = "http://mobile.iliangcang.com/brand/brandList?app_key=Android&count=20&page=1&sig=430BD99E6C913B8B8C3ED109737ECF15%7C830952120106768&v=1.0";

    //品牌子类API,根据 brand_id来获取不同品牌的数据，这里截取2部分，中间加ID
    public static final String BrandChildUrl01 = "http://mobile.iliangcang.com/brand/brandShopList?app_key=Android&brand_id=";
    public static final String BrandChildUrl02 = "&count=20&page=1&sig=430BD99E6C913B8B8C3ED109737ECF15%7C830952120106768&v=1.0";

    //专题总API
    public static final String TOPICFRAGMENTURL = "http://mobile.iliangcang.com/goods/shopSpecial?app_key=Android&count=10&page=1&sig=3780CB0808528F7CE99081D295EE8C0F%7C116941220826768&uid=626138098&user_token=0516ed9429352c8e1e3bd11c63ba6f54&v=1.0";

    //礼物API

    //点击礼物第一个图片API
    public static final String GIFTFRAGMENT_FIRSTPICURL = "http://mobile.iliangcang.com/goods/goodsList?app_key=Android&count=10&list_id=7&page=1&sig=73760B2740FA36D5A273523FBC9295FE%7C285269230036268&v=1.0";
    //节日
    public static final String GIFTFRAGMENT_HOLIDAY = "http://mobile.iliangcang.com/goods/goodsList?app_key=Android&count=10&list_id=1&page=1&sig=DFD7151CC9D607E396FE108FE270FFF3%7C366534120395468&v=1.0";
    //爱情
    public static final String GIFFRAGMENT_LOVE = "http://mobile.iliangcang.com/goods/goodsList?app_key=Android&count=10&list_id=2&page=1&sig=73760B2740FA36D5A273523FBC9295FE%7C285269230036268&v=1.0";
    //生日
    public static final String GIFFRAGMENT_BIRTHDAY = "http://mobile.iliangcang.com/goods/goodsList?app_key=Android&count=10&list_id=3&page=1&sig=73760B2740FA36D5A273523FBC9295FE%7C285269230036268&v=1.0";
    //朋友
    public static final String GIFFRAGMENT_FRIEND = "http://mobile.iliangcang.com/goods/goodsList?app_key=Android&count=10&list_id=4&page=1&sig=73760B2740FA36D5A273523FBC9295FE%7C285269230036268&v=1.0";
    //孩子
    public static final String GIFFRAGMENT_CHILD = "http://mobile.iliangcang.com/goods/goodsList?app_key=Android&count=10&list_id=5&page=1&sig=73760B2740FA36D5A273523FBC9295FE%7C285269230036268&v=1.0";
    //父母
    public static final String GIFFRAGMENT_PARENTS = "http://mobile.iliangcang.com/goods/goodsList?app_key=Android&count=10&list_id=6&page=1&sig=73760B2740FA36D5A273523FBC9295FE%7C285269230036268&v=1.0";

    //达人页面：

    //PopUpWindow里面的API
    //1.默认推荐
    public static final String TRENDPERSONDEFAULTRECOMMEND = "http://mobile.iliangcang.com/user/masterList?app_key=Android&count=18&page=1&sig=BF287AF953103F390674E73DDA18CFD8|639843030233268&v=1.0";
   // 2.最多推荐
    public static final String TRENDPERSONMOSTRECOMMEND = "http://mobile.iliangcang.com/user/masterList?app_key=Android&count=18&orderby=goods_sum&page=1&sig=05D2057FE3D726A43A94505807516FC3%7C136072130089168&v=1.0";
    //3.最受欢迎
    public static final String TRENDPERSONMOSTWELCOME = "http://mobile.iliangcang.com/user/masterList?app_key=Android&count=18&orderby=followers&page=9&sig=05D2057FE3D726A43A94505807516FC3|136072130089168&v=1.0";
    //4.最新推荐
    public static final String TRENDPERSONLATESTRECOMMEND = "http://mobile.iliangcang.com/user/masterList?app_key=Android&count=18&orderby=reg_time&page=9&sig=05D2057FE3D726A43A94505807516FC3|136072130089168&v=1.0";
    //5.最新加入
    public static final String TRENDPERSONLATESTJOIN = "http://mobile.iliangcang.com/user/masterList?app_key=Android&count=18&orderby=action_time&page=9&sig=05D2057FE3D726A43A94505807516FC3|136072130089168&v=1.0";

    //达人页面点击某个人触发的API(根据 owner_id 来切换)
    public static final String TRENDPERSONDETAILURL01 = "http://mobile.iliangcang.com/user/masterFollowed?app_key=Android&count=12&owner_id=";
    public static final String TRENDPERSONDETAILURL02 = "&page=1&sig=BF287AF953103F390674E73DDA18CFD8%7C639843030233268&v=1.0";

    //达人详情页面的推荐fragment的API(根据 owner_id 来切换)
    public static final String TRENDPERSONDETAILRECOMMENDURL01 = "http://mobile.iliangcang.com/user/masterListInfo?app_key=Android&count=10&owner_id=";
    public static final String TRENDPERSONDETAILRECOMMENDURL02 = "&page=1&sig=BF287AF953103F390674E73DDA18CFD8%7C639843030233268&v=1.0";

    //达人详情页面喜欢的fragment的API
    public static final String TRENDPERSONDETAILLIKEURL01 = "http://mobile.iliangcang.com/user/masterLike?app_key=Android&count=10&owner_id=";
    public static final String TRENDPERSONDETAILLIKEURL02 = "&page=1&sig=BF287AF953103F390674E73DDA18CFD8%7C639843030233268&v=1.0";

    //达人详情页面关注的fragment的API
    public static final String TRENDPERSONDETAILCAREURL01 = "http://mobile.iliangcang.com/user/masterFollow?app_key=Android&count=10&owner_id=";
    public static final String TRENDPERSONDETAILCAREURL02 = "&page=1&sig=BF287AF953103F390674E73DDA18CFD8%7C639843030233268&v=1.0";

    //达人详情页面粉丝的fragment的API
    public static final String TRENDPERSONDETAILFANSURL01 = "http://mobile.iliangcang.com/user/masterFollowed?app_key=Android&count=10&owner_id=";
    public static final String TRENDPERSONDETAILFANSURL02 = "&page=1&sig=BF287AF953103F390674E73DDA18CFD8%7C639843030233268&v=1.0";

    //商品的详细页面的API,根据 goods_id 来获取不同商品的数据，这里截取2部分，中间加ID
    public static final String GOODSDETAILURL01 = "http://mobile.iliangcang.com/goods/goodsDetail?app_key=Android&goods_id=";
    public static final String GOODSDETAILURL02 = "&sig=BF287AF953103F390674E73DDA18CFD8%7C639843030233268&v=1.0";


    /**
     * 杂志页面
     */

    //杂志页面中作者API
    public static final String MAGAZINEAUTHERURL = "http://mobile.iliangcang.com/topic/magazineAuthorList?app_key=Android&sig=2FA0974FFF1BC3DFA562AA63C8B5A84F%7C118265010131868&v=1.0";

    //作者API明细(根据author_id来切换不同页面)
    public static final String MAGAZINEAUTHERDETAILURL01 = "http://mobile.iliangcang.com/topic/magazineList?app_key=Android&author_id=";
    public static final String MAGAZINEAUTHERDETAILURL02 = "&sig=2FA0974FFF1BC3DFA562AA63C8B5A84F%7C118265010131868&v=1.0";

    //杂志列表页面API
    public static final String MAGAZINELISTURL = "http://mobile.iliangcang.com/topic/magazineList?app_key=Android&author_id=1&sig=2FA0974FFF1BC3DFA562AA63C8B5A84F%7C118265010131868&v=1.0";


    /**
     * 分享页面：
     */
    //这里用百思不得姐的推荐API
    public static final String SHARERECOMMENDURL = "http://s.budejie.com/topic/list/jingxuan/1/budejie-android-6.6.3/0-20.json";

    //百思不得姐的 段子的 API
    public static final String SHAREREDUANZIURL = "http://s.budejie.com/topic/tag-topic/64/hot/budejie-android-6.6.3/0-20.json";
}
