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
                            <xsl:when test="count(part/section[position() = 1 and @level=1]/heading[@class='title'])=0">
                                <xsl:value-of select="'Untitled Document'"/>
                            </xsl:when>
                            <xsl:otherwise>
                                <!-- <xsl:value-of select="documentinfo/property[@name='title']/@value"/>-->
                                <xsl:apply-templates select="part/section[position() = 1 and @level=1]/heading[@class='title']"/>
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
    <xsl:template match="heading[@class='dippSubtitle']" mode="articleinfo-mode">
        <subtitle>
            <xsl:apply-templates/>
        </subtitle>
    </xsl:template>
    <xsl:template match="section" mode="articleinfo-mode">
        <xsl:apply-templates mode="articleinfo-mode"/>
    </xsl:template>
    <xsl:template match="heading[@class='authorgroup']" mode="articleinfo-mode">
        <authorblurb>
            <xsl:apply-templates select="../par"/>
        </authorblurb>
    </xsl:template>
    
<!--    <xsl:template match="par[@class='author']" mode="articleinfo-mode">
            <xsl:apply-templates select="self::node()"/>
    </xsl:template>
    <xsl:template match="par[@class='authorinfo']" mode="articleinfo-mode">
            <xsl:apply-templates select="self::node()"/>
    </xsl:template>
-->    
<!--
    <xsl:template match="par[@class='bmmAffiliation']" mode="articleinfo-mode">
        <affiliation>
            <xsl:apply-templates select="self::node()"/>
        </affiliation>
    </xsl:template>
    <xsl:template match="par[@class='bmmCorresponding']" mode="articleinfo-mode">
        <address>
            <xsl:apply-templates select="self::node()"/>
        </address>
    </xsl:template>
-->
    <xsl:template match="par[@class='Copyright']" mode="articleinfo-mode">
        <legalnotice>
            <xsl:apply-templates select="self::node()"/>
        </legalnotice>
    </xsl:template>
<!--<xsl:template match="heading[@class='AbstractDE']" mode="articleinfo-mode">
        <abstract>
            <xsl:if test="@xml:lang">
                <xsl:attribute name="lang">
                    <xsl:value-of select="attribute::xml:lang"/>
                </xsl:attribute>
            </xsl:if>
            <xsl:apply-templates select="../par"/>
        </abstract>
    </xsl:template>
-->
   <xsl:template match="heading[@class='abstractgroup']" mode="articleinfo-mode">
        <xsl:apply-templates select="../par" mode="articleinfoabstract-mode"/>
    </xsl:template>

    <xsl:template match="par[@class='abstract']" mode="articleinfoabstract-mode">
        <abstract>
            <xsl:attribute name="lang">de</xsl:attribute>
            <xsl:apply-templates select="self::node()"/>
        </abstract>
    </xsl:template>
    <xsl:template match="par[@class='abstractDE']" mode="articleinfoabstract-mode">
        <abstract>
            <xsl:attribute name="lang">de</xsl:attribute>
            <xsl:apply-templates select="self::node()"/>
        </abstract>
    </xsl:template>
    <xsl:template match="par[@class='abstractEN']" mode="articleinfoabstract-mode">
        <abstract>
            <xsl:attribute name="lang">en</xsl:attribute>
            <xsl:apply-templates select="self::node()"/>
        </abstract>
    </xsl:template>
    <xsl:template match="*" mode="articleinfo-mode"/>

	<!-- ####################
     GENTEXT element
	 ####################-->
	<xsl:template match="gentext">
	  <xsl:choose>
	  
		<!-- index location -->
		<xsl:when test="(@type = 'INDEX')">
		  <xsl:if test="count(ancestor::par)=0"> <!-- not in a par element -->
			<index><xsl:comment> Index is to be generated here by processing system </xsl:comment></index>
		  </xsl:if>
		</xsl:when>
	  
		<xsl:otherwise>
	<!--      <xsl:if test="(@type != 'upcast-FOOTNOTENUMBER') and (@type != 'upcast-HEADINGNUMBER') and (@type != 'upcast-ANNOTATIONNUMBER')">
	-->
		  <xsl:if test="(@type != 'upcast-FOOTNOTENUMBER') and (@type != 'upcast-ANNOTATIONNUMBER')">
			<xsl:choose>
			  <xsl:when test="$usePIsForGentext='true'">
				<xsl:processing-instruction name="uc-gentext">
				  <xsl:text>type="</xsl:text><xsl:value-of select="@type"/><xsl:text>" value="</xsl:text><xsl:value-of select="descendant::text()"/><xsl:text>"</xsl:text>
				</xsl:processing-instruction>
			  </xsl:when>
			  <xsl:otherwise>
				<phrase>
				  <xsl:attribute name="role">
					<xsl:text>GEN_</xsl:text><xsl:value-of select="@type" />
				  </xsl:attribute>
				  <xsl:apply-templates />
				</phrase>
			  </xsl:otherwise>
			</xsl:choose>
		  </xsl:if>
		</xsl:otherwise>
		
	  </xsl:choose>
	</xsl:template>

</xsl:stylesheet>
