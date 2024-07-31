# 目录穿越利用以及绕过
- https://websec.readthedocs.io/zh/latest/vuln/pathtraversal.html
- https://github.com/1135/notes/blob/master/web_vul_PathTraversal.md


根本原因：
    在使用 new File(filePath)的时候，filePath可控，并且未对filePath做严格校验，攻击者可以通过 ../ 的方式进行目录条约，实现任意文件下载或
跨目录上传文件漏洞。

# 任意文件下载

# Zip Slip系列漏洞

作为一名开发人员，您经常需要处理zip文件。例如，考虑上传功能或处理作为zip文件上传的一堆CSV文件。Snyk安全团队发现并负责任地披露了一个整洁的漏洞。它使用路径遍历，可以在提取文件时使用。通过路径遍历，您可以尝试覆盖目标文件夹之外的文件。例如，您可以在提取zip文件时覆盖ls命令。在每次用户在ls中键入命令时，将该命令替换为一些额外的恶意操作后，您可以在向用户显示实际命令之前将清单的结果发送到服务器。所以你最终会得到远程命令执行。
