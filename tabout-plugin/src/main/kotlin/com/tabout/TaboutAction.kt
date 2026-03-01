package com.tabout

import com.intellij.codeInsight.lookup.LookupManager
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.command.WriteCommandAction
import com.intellij.openapi.editor.Editor

class TaboutAction : AnAction() {

    private val jumpOverChars = setOf(')', ']', '}', '"', '\'', '`', '>', ';')

    override fun actionPerformed(e: AnActionEvent) {
        val editor = e.getData(CommonDataKeys.EDITOR) ?: return

        val project = e.project
        if (project != null && LookupManager.getActiveLookup(editor) != null) {
            val tabAction = com.intellij.openapi.actionSystem.ActionManager.getInstance()
                .getAction("EditorTab")
            tabAction?.actionPerformed(e)
            return
        }

        if (!tryTabout(editor)) {
            val tabAction = com.intellij.openapi.actionSystem.ActionManager.getInstance()
                .getAction("EditorTab")
            tabAction?.actionPerformed(e)
        }
    }

    private fun tryTabout(editor: Editor): Boolean {
        val document = editor.document
        val caretModel = editor.caretModel
        val carets = caretModel.allCarets
        var didJump = false

        for (caret in carets) {
            if (caret.hasSelection()) continue

            val offset = caret.offset
            val text = document.charsSequence

            if (offset >= text.length) continue

            val charAtCursor = text[offset]

            if (charAtCursor in jumpOverChars) {
                if (charAtCursor == '"' || charAtCursor == '\'' || charAtCursor == '`') {
                    if (!isInsideQuotePair(text, offset, charAtCursor)) continue
                }

                WriteCommandAction.runWriteCommandAction(editor.project) {
                    caret.moveToOffset(offset + 1)
                }
                didJump = true
            }
        }

        return didJump
    }

    private fun isInsideQuotePair(text: CharSequence, offset: Int, quoteChar: Char): Boolean {
        var lineStart = offset
        while (lineStart > 0 && text[lineStart - 1] != '\n') {
            lineStart--
        }
        var count = 0
        for (i in lineStart until offset) {
            if (text[i] == quoteChar && (i == 0 || text[i - 1] != '\\')) {
                count++
            }
        }
        return count % 2 == 1
    }

    override fun update(e: AnActionEvent) {
        val editor = e.getData(CommonDataKeys.EDITOR)
        e.presentation.isEnabledAndVisible = editor != null
    }
}
