<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:xlink="http://www.w3.org/1999/xlink" xmlns:html="http://www.w3.org/HTML/1998/html4"
    xmlns:my-class="xalan://de.infinityloop.upcast.exportfilters.ExportFilter11"
    xmlns:xalan="http://xml.apache.org/xalan" exclude-result-prefixes="my-class html xlink xalan">
    <!--
    -->
    <xsl:import href="db42.xsl"/>
    <!--
    -->
    <!-- ####################
  DOCUMENT element
     ####################-->
    <xsl:template match="document">
        <xsl:choose>
            <!-- book -->
            <xsl:when test="$rootElement='book'">
                <book>
                    <xsl:if test="@xml:lang">
                        <xsl:attribute name="lang">
                            <xsl:value-of select="attribute::xml:lang"/>
                        </xsl:attribute>
                    </xsl:if>
                    <title>
                        <xsl:value-of select="documentinfo/property[@name='title']/@value"/>
                    </title>
                    <xsl:apply-templates/>
                </book>
            </xsl:when>
            <!-- article -->
            <xsl:when test="$rootElement='article'">
                <article>
                    <xsl:if test="@xml:lang">
                        <xsl:attribute name="lang">
                            <xsl:value-of select="attribute::xml:lang"/>
                        </xsl:attribute>
                    </xsl:if>
                    <title>
                        <xsl:choose>
                            <xsl:when test="count(part/section[position() = 1 and @level=1]/heading[@class='Title'])=0">
                                <xsl:value-of select="'Untitled Document'"/>
                            </xsl:when>
                            <xsl:otherwise>
                                <!-- <xsl:value-of select="documentinfo/property[@name='title']/@value"/>-->
                                <xsl:apply-templates select="part/section[position() = 1 and @level=1]/heading[@class='Title']"/>
                            </xsl:otherwise>
                        </xsl:choose>
                    </title>
                    <!-- process first section, contains articleinfo data -->
                    <xsl:element name="{$rootElement}info">
                        <xsl:apply-templates select="part/section[position() = 1]" mode="articleinfo-mode"/>
                        <!--   <xsl:apply-templates select="part/section[position() = 1]"/>-->
                    </xsl:element>
                    <!-- end articleinfo data -->
                    <!-- now process actual content of article -->
                    <xsl:apply-templates select="part/section[position() &gt; 1]"/>
                </article>
            </xsl:when>
            <!-- custom -->
            <xsl:when test="$rootElement='custom'">
                <article>
                    <title>Custom structure</title>
                    <para>You need to modify the stylesheet appropriately to generate DocBook output
                        with custom structure.</para>
                </article>
            </xsl:when>
        </xsl:choose>
        <xsl:comment> Document conversion from RTF by upCast <xsl:value-of
            select="my-class:getVersion()"/>, (c) 1999-2004 infinity-loop
            &lt;www.infinity-loop.de&gt; </xsl:comment>
    </xsl:template>
    <xsl:template match="heading[@class='Subtitle']" mode="articleinfo-mode">
        <subtitle>
            <xsl:apply-templates/>
        </subtitle>
    </xsl:template>
    <xsl:template match="section" mode="articleinfo-mode">
        <xsl:apply-templates mode="articleinfo-mode"/>
    </xsl:template>
    <xsl:template match="par[@class='Verfasser']" mode="articleinfo-mode">
        <authorblurb>
            <xsl:apply-templates select="self::node()"/>
        </authorblurb>
    </xsl:template>
    <xsl:template match="par[@class='Copyright']" mode="articleinfo-mode">
        <copyright>
            <xsl:apply-templates select="self::node()"/>
        </copyright>
    </xsl:template>
    <!-- works only if xml:lang of article is reliable -->
    <!--<xsl:template match="heading[starts-with(@class,'Abstract')]" mode="articleinfo-mode">
        <abstract>
            <xsl:choose>
                <xsl:when test="@xml:lang">
                    <xsl:attribute name="lang">
                        <xsl:value-of select="attribute::xml:lang"/>
                    </xsl:attribute>
                </xsl:when>
                <xsl:otherwise>
                    <xsl:if test="/document/@xml:lang">
                        <xsl:attribute name="lang">
                            <xsl:value-of select="/document/attribute::xml:lang"/>
                        </xsl:attribute>
                    </xsl:if>
                </xsl:otherwise>
            </xsl:choose>
            <xsl:apply-templates select="../par"/>
        </abstract>-->
    <xsl:template match="heading[@class='AbstractDE']" mode="articleinfo-mode">
        <abstract lang="de">
            <xsl:apply-templates select="../par"/>
        </abstract>
    </xsl:template>
    <xsl:template match="heading[@class='AbstractEN']" mode="articleinfo-mode">
        <abstract lang="en">
            <xsl:apply-templates select="../par"/>
        </abstract>
    </xsl:template>
    <xsl:template match="*" mode="articleinfo-mode"/>
</xsl:stylesheet>
