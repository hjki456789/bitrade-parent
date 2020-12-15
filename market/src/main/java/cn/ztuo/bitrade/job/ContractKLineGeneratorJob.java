package cn.ztuo.bitrade.job;

import org.springframework.stereotype.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.scheduling.annotation.*;
import cn.ztuo.bitrade.processor.*;
import java.util.*;
import cn.ztuo.bitrade.utils.*;
import org.slf4j.*;

@Component
public class ContractKLineGeneratorJob
{
    private static final Logger log;
    @Autowired
    private CoinProcessorFactory processorFactory;
    
    @Scheduled(cron = "0 * * * * *")
    public void handle5minKLine() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     3: astore_1        /* calendar */
        //     4: getstatic       cn/ztuo/bitrade/job/ContractKLineGeneratorJob.log:Lorg/slf4j/Logger;
        //     7: ldc             "\u5206\u949fK\u7ebf:{}"
        //     9: aload_1         /* calendar */
        //    10: invokevirtual   java/util/Calendar.getTime:()Ljava/util/Date;
        //    13: invokeinterface org/slf4j/Logger.info:(Ljava/lang/String;Ljava/lang/Object;)V
        //    18: aload_1         /* calendar */
        //    19: bipush          13
        //    21: iconst_0       
        //    22: invokevirtual   java/util/Calendar.set:(II)V
        //    25: aload_1         /* calendar */
        //    26: bipush          14
        //    28: iconst_0       
        //    29: invokevirtual   java/util/Calendar.set:(II)V
        //    32: aload_1         /* calendar */
        //    33: invokevirtual   java/util/Calendar.getTimeInMillis:()J
        //    36: lstore_2        /* time */
        //    37: aload_1         /* calendar */
        //    38: bipush          12
        //    40: invokevirtual   java/util/Calendar.get:(I)I
        //    43: istore          minute
        //    45: aload_1         /* calendar */
        //    46: bipush          11
        //    48: invokevirtual   java/util/Calendar.get:(I)I
        //    51: istore          hour
        //    53: aload_0         /* this */
        //    54: getfield        cn/ztuo/bitrade/job/ContractKLineGeneratorJob.processorFactory:Lcn/ztuo/bitrade/processor/CoinProcessorFactory;
        //    57: ldc             "contract"
        //    59: invokevirtual   cn/ztuo/bitrade/processor/CoinProcessorFactory.getProcessorMap:(Ljava/lang/String;)Ljava/util/HashMap;
        //    62: lload_2         /* time */
        //    63: iload           minute
        //    65: iload           hour
        //    67: invokedynamic   BootstrapMethod #0, accept:(JII)Ljava/util/function/BiConsumer;
        //    72: invokevirtual   java/util/HashMap.forEach:(Ljava/util/function/BiConsumer;)V
        //    75: return         
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Could not infer any expression.
        //     at com.strobel.decompiler.ast.TypeAnalysis.runInference(TypeAnalysis.java:374)
        //     at com.strobel.decompiler.ast.TypeAnalysis.run(TypeAnalysis.java:96)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:344)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:782)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:675)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:552)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:519)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:161)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:150)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:125)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at us.deathmarine.luyten.FileSaver.doSaveJarDecompiled(FileSaver.java:192)
        //     at us.deathmarine.luyten.FileSaver.access$300(FileSaver.java:45)
        //     at us.deathmarine.luyten.FileSaver$4.run(FileSaver.java:112)
        //     at java.lang.Thread.run(Unknown Source)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @Scheduled(cron = "0 0 * * * *")
    public void handleHourKLine() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: getfield        cn/ztuo/bitrade/job/ContractKLineGeneratorJob.processorFactory:Lcn/ztuo/bitrade/processor/CoinProcessorFactory;
        //     4: ldc             "contract"
        //     6: invokevirtual   cn/ztuo/bitrade/processor/CoinProcessorFactory.getProcessorMap:(Ljava/lang/String;)Ljava/util/HashMap;
        //     9: invokedynamic   BootstrapMethod #1, accept:()Ljava/util/function/BiConsumer;
        //    14: invokevirtual   java/util/HashMap.forEach:(Ljava/util/function/BiConsumer;)V
        //    17: return         
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Could not infer any expression.
        //     at com.strobel.decompiler.ast.TypeAnalysis.runInference(TypeAnalysis.java:374)
        //     at com.strobel.decompiler.ast.TypeAnalysis.run(TypeAnalysis.java:96)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:344)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:782)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:675)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:552)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:519)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:161)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:150)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:125)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at us.deathmarine.luyten.FileSaver.doSaveJarDecompiled(FileSaver.java:192)
        //     at us.deathmarine.luyten.FileSaver.access$300(FileSaver.java:45)
        //     at us.deathmarine.luyten.FileSaver$4.run(FileSaver.java:112)
        //     at java.lang.Thread.run(Unknown Source)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @Scheduled(cron = "0 0 0 * * *")
    public void handleDayKLine() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: getfield        cn/ztuo/bitrade/job/ContractKLineGeneratorJob.processorFactory:Lcn/ztuo/bitrade/processor/CoinProcessorFactory;
        //     4: ldc             "contract"
        //     6: invokevirtual   cn/ztuo/bitrade/processor/CoinProcessorFactory.getProcessorMap:(Ljava/lang/String;)Ljava/util/HashMap;
        //     9: invokedynamic   BootstrapMethod #2, accept:()Ljava/util/function/BiConsumer;
        //    14: invokevirtual   java/util/HashMap.forEach:(Ljava/util/function/BiConsumer;)V
        //    17: return         
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Could not infer any expression.
        //     at com.strobel.decompiler.ast.TypeAnalysis.runInference(TypeAnalysis.java:374)
        //     at com.strobel.decompiler.ast.TypeAnalysis.run(TypeAnalysis.java:96)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:344)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:782)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:675)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:552)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:519)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:161)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:150)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:125)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at us.deathmarine.luyten.FileSaver.doSaveJarDecompiled(FileSaver.java:192)
        //     at us.deathmarine.luyten.FileSaver.access$300(FileSaver.java:45)
        //     at us.deathmarine.luyten.FileSaver$4.run(FileSaver.java:112)
        //     at java.lang.Thread.run(Unknown Source)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    static {
        log = LoggerFactory.getLogger((Class)ContractKLineGeneratorJob.class);
    }
}
