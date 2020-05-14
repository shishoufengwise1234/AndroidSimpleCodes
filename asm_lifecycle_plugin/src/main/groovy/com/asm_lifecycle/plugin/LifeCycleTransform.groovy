package com.asm_lifecycle.plugin

import com.android.build.api.transform.*
import com.android.build.gradle.internal.pipeline.TransformManager
import com.android.utils.FileUtils
import com.asm_lifecycle.LifecycleClassVisitor
import groovy.io.FileType
import org.objectweb.asm.ClassReader
import org.objectweb.asm.ClassVisitor
import org.objectweb.asm.ClassWriter

public class LifeCycleTransform extends Transform {

    private String TAG = "LifeCycleTransform"

    @Override
    String getName() {
        return "LifeCycleLogTransform"
    }

    @Override
    Set<QualifiedContent.ContentType> getInputTypes() {
        Set<QualifiedContent.ContentType> set = new HashSet<>(1)
        set.add(QualifiedContent.DefaultContentType.CLASSES)

//        return set
        return TransformManager.CONTENT_CLASS
    }


    /*

    这个方法规定自定义 Transform 检索的范围，具体有以下几种取值：
     */

    @Override
    Set<? super QualifiedContent.Scope> getScopes() {

        Set<QualifiedContent.Scope> scopeSet = new HashSet<>()

        scopeSet.add(QualifiedContent.Scope.EXTERNAL_LIBRARIES) //只有外部库
        scopeSet.add(QualifiedContent.Scope.PROJECT) //只有项目内容
        scopeSet.add(QualifiedContent.Scope.PROJECT_LOCAL_DEPS) //只有项目的本地依赖
        scopeSet.add(QualifiedContent.Scope.PROVIDED_ONLY) //只提供本地或远程依赖项
        scopeSet.add(QualifiedContent.Scope.SUB_PROJECTS) //只有子项目
        scopeSet.add(QualifiedContent.Scope.SUB_PROJECTS_LOCAL_DEPS) //只有子项目的本地依赖项
        scopeSet.add(QualifiedContent.Scope.TESTED_CODE) //由当前变量包括依赖项测试的代码

//        return scopeSet
        return TransformManager.PROJECT_ONLY
    }

    //表示当前 Transform 是否支持增量编译，我们不需要增量编译，所以直接返回 false 即可。
    @Override
    boolean isIncremental() {
        return false
    }


    @Override
    public void transform(TransformInvocation transformInvocation) throws TransformException,
            InterruptedException, IOException {
        super.transform(transformInvocation)

        System.out.println(TAG + ":transform() start ")

        //获取所有 .class 文件
        Collection<TransformInput> inputCollection = transformInvocation.inputs
        TransformOutputProvider outputProvider = transformInvocation.outputProvider

        inputCollection.each { TransformInput transformInput ->
            System.out.println(TAG + ":transform() inputCollection.each ")

            transformInput.directoryInputs.each { DirectoryInput directoryInput ->
                File dir = directoryInput.file

                System.out.println(TAG + ":transform() transformInput.directoryInputs.each ")

                if (dir) {
                    System.out.println(TAG + ":transform() transformInput.directoryInputs.each -> dir ")

                    dir.traverse(type: FileType.FILES, nameFilter: ~/.*\.class/) { File file ->
                        System.out.println("find class: " + file.name)

                        //对class文件进行读取与解析
                        ClassReader classReader = new ClassReader(file.bytes)

                        // 对class文件写入
                        ClassWriter classWriter = new ClassWriter(classReader,ClassWriter.COMPUTE_MAXS)

                        //访问class文件相应内容、解析到某一个结构就会通知到 ClassVisitor 内部方法
                        ClassVisitor classVisitor = new LifecycleClassVisitor(classWriter)

                        // 依次调用 classVisitor内部方法
                        classReader.accept(classVisitor,ClassReader.EXPAND_FRAMES)

                        byte [] bytes = classWriter.toByteArray()

                        //通过文件流写入方式覆盖掉原先的内容，实现class文件的改写。
                        FileOutputStream fis = new FileOutputStream(file.path)
                        fis.write(bytes)
                        fis.close()

                    }
                }
                def dest = outputProvider.getContentLocation(directoryInput.name,directoryInput.contentTypes,
                        directoryInput.scopes,Format.DIRECTORY)
                FileUtils.copyDirectory(directoryInput.file,dest)
            }
        }


    }
}