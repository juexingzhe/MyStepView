# MyStepView
整个控件主要分成两个部分，上面图形部分StepViewIndicator，下面是一个RelativeLayout，用于动态添加TextView。


类似于网上某宝购物网站的订单跟踪流程，下单-->送货-->签收等等，我们先看下本文要实现的demo。下单界面点击下一步流程会走到送货界面，再次点击下一步会到签收界面。状态分成完成和未完成。
完成的是下单，未完成的是送货和签收过程

![1.png](http://upload-images.jianshu.io/upload_images/2608779-dae3f587d75304d6.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

完成的是下单和送货，未完成的是签收

![2.png](http://upload-images.jianshu.io/upload_images/2608779-96b57cb5b0eabcdd.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

完成的是下单、 送货和签收状态

![3.png](http://upload-images.jianshu.io/upload_images/2608779-c093dd44fa2d1275.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


使用方式：

new StepView.Builder().setTextIndicator(mTextIndicator)//文字列表
                .setCompleteTextColor(ContextCompat.getColor(mStepCompleteView.getStepView().getContext(), R.color.complete_text_color))//完成流程文字的颜色
                .setUncompleteTextColor(ContextCompat.getColor(mStepCompleteView.getStepView().getContext(), R.color.uncomplete_text_color))//未完成流程文字的颜色
                .setCompleteDrawableResIdList(mCompleteDrawableResIdList)//完成流程的背景图片集合
                .setUncompleteDrawableResIdList(mUncompleteDrawableResIdList)//未完成流程的背景图片集合
                .setCurrrentPos(mCurrentPos) //更新当前位置
                .build(mStepCompleteView.getStepView());
                
                
                
具体可参考链接：
http://www.jianshu.com/p/32df128b45ed
