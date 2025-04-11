package cn.abalone.gocq.plugins;

import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import top.nkdark.gocq.EImageEffect;
import top.nkdark.gocq.EImageSubType;
import top.nkdark.gocq.EImageType;
import top.nkdark.gocq.bot.Bot;
import top.nkdark.gocq.bot.BotPlugin;
import top.nkdark.gocq.proto.GroupMessageEvent;
import top.nkdark.gocq.proto.PrivateMessageEvent;
import top.nkdark.gocq.proto.guild.GuildMessageEvent;
import top.nkdark.gocq.util.CQCode;

import java.util.ArrayList;

import static cn.abalone.gocq.tools.RandomUtils.*;
import static cn.abalone.gocq.tools.FoodTemplates.*;
import static cn.abalone.gocq.tools.LuckTemplates.*;


@Component
public class JavaTestPlugin extends BotPlugin {
    /**
     * 可选，日志工具
     * 也可使用 lombok 的 @Slf4j 注解
     */
    private final Logger log = LoggerFactory.getLogger(JavaTestPlugin.class);


    /**
     * 收到私聊消息时会调用这个方法
     *
     * @param bot   机器人对象，用于调用API，例如发送私聊消息 sendPrivateMsg
     * @param event 事件对象，用于获取消息内容、群号、发送者QQ等
     * @return 是否继续调用下一个插件, `MatchedAndBlock` 表示不继续, `NotMatch` 表示继续
     */
    @Override
    public int onPrivateMessage(@NotNull Bot bot, @NotNull PrivateMessageEvent event) {
        // 获取 发送者QQ 和 消息内容
        long userId = event.getUserId();
        String msg = event.getMessage();
        // 控制台打印
        log.info(msg);
        // 将收到的消息复读
        bot.sendPrivateMsg(userId, msg, false);
        // 继续执行下一个插件
        return NotMatch;
    }

    /**
     * 收到群消息时会调用这个方法
     *
     * @param bot   机器人对象，用于调用API，例如发送私聊消息 sendGroupMsg
     * @param event 事件对象，用于获取消息内容、群号、发送者QQ等
     * @return 是否继续调用下一个插件, `MatchedAndBlock` 表示不继续, `NotMatch` 表示继续
     */
    @Override
    public int onGroupMessage(@NotNull Bot bot, @NotNull GroupMessageEvent event) {
        // 获取 消息内容 群号 发送者QQ
        String msg = event.getMessage();
        long groupId = event.getGroupId();
        long userId = event.getUserId();
        // 控制台打印日志
        log.info(msg);
        // 编辑回复内容
        String respContent = CQCode.INSTANCE.at(userId);
        String extend = "";
        if (msg.length() < 10) {
            if (msg.contains("吃什么")){
                int foodSize = food.length-1;
                int yueHaiPlaceSize = yueHaiPlaces.length-1;
                int liHuPlaceSize = liHuPlaces.length-1;
                ArrayList<Integer>rFood = getNumber(0,foodSize,3),
                        rYueHai = getNumber(0,yueHaiPlaceSize,1),
                        rliHu = getNumber(0,liHuPlaceSize,1);
                extend+="\n饭堂推荐："+yueHaiPlaces[rYueHai.get(0)]+'，'+liHuPlaces[rliHu.get(0)]+"\n";
                extend+="可以试试："+food[rFood.get(0)]+"，"+food[rFood.get(1)]+"，"+food[rFood.get(2)];
            } else if (msg.contains("运势")){
                int thingsSize=things.length-1,sentenceSize=sentence.length-1,luckSize = luck.length-1;
                ArrayList<Integer> rThing = getNumber(0,thingsSize,2),
                        rSentence = getNumber(0,sentenceSize,1),
                        rLuck = getNumber(0,luckSize,1);
                int randomJi=rThing.get(0),
                        randomYi = rThing.get(1),
                        randomSentence= rSentence.get(0),
                        randomLuck = rLuck.get(0);
                extend="今日运势："+luck[randomLuck]+"\n"+"宜："+things[randomYi]+"  "+"忌："+things[randomJi]+"\n"+sentence[randomSentence];
            } else if (msg.equals("114514")) {
                extend = "";
            } else if (msg.contains("粤海地图")) {
                String imgPath = "C:/Users/Abalone/Desktop/粤海.png";
                extend = CQCode.INSTANCE.image("file:///"+imgPath, EImageType.Plain, EImageSubType.Normal, "", false, EImageEffect.Normal, 2);
            } else if (msg.contains("丽湖地图")) {
                String imgPath = "C:/Users/Abalone/Desktop/丽湖.jpg";
                extend = CQCode.INSTANCE.image("file:///"+imgPath, EImageType.Plain, EImageSubType.Normal, "", false, EImageEffect.Normal, 2);
            }
        }
        if (!extend.isEmpty()) {
            bot.sendGroupMsg(groupId, respContent + extend, false);
        }
        // 不继续执行下一个插件
        return MatchedAndBlock;
    }

    /**
     * 收到频道消息时会调用这个方法
     *
     * @param bot   机器人对象，用于调用API，例如发送私聊消息 sendGroupMsg
     * @param event 事件对象，用于获取消息内容、群号、发送者QQ等
     * @return 是否继续调用下一个插件, `MatchedAndBlock` 表示不继续, `NotMatch` 表示继续
     */
    @Override
    public int onGuildMessage(@NotNull Bot bot, @NotNull GuildMessageEvent event) {
        log.info(event.toString());
        return MatchedAndBlock;
    }
}
