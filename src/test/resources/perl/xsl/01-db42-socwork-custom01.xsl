<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:xlink="http://www.w3.org/1999/xlink" xmlns:html="http://www.w3.org/HTML/1998/html4"
    xmlns:my-class="xalan://de.infinityloop.upcast.exportfilters.ExportFilter11"
    xmlns:xalan="http://xml.apache.org/xalan" exclude-result-prefixes="my-class html xlink xalan">
    <!--
    -->
    <xsl:import href="db42-1.7.xsl"/>
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
                            <xsl:when test="count(part/section[position() = 1 and @level=1]/heading[@class='swsTitle'])=0">
                                <xsl:value-of select="'Untitled Document'"/>
                            </xsl:when>
                            <xsl:otherwise>
                                <!-- <xsl:value-of select="documentinfo/property[@name='title']/@value"/>-->
                                <xsl:apply-templates select="part/section[position() = 1 and @level=1]/heading[@class='swsTitle']"/>
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
    
    <xsl:template match="section" mode="articleinfo-mode">
        <xsl:apply-templates mode="articleinfo-mode"/>
    </xsl:template>
    <xsl:template match="par[@class='swsAuthorInstitute']" mode="articleinfo-mode">
        <authorblurb>
            <xsl:apply-templates select="self::node()"/>
        </authorblurb>
    </xsl:template>
    <xsl:template match="par[@class='Copyright']" mode="articleinfo-mode">
        <copyright>
            <xsl:apply-templates select="self::node()"/>
        </copyright>
    </xsl:template>
    <xsl:template match="par[@class='swsAbstract']" mode="articleinfo-mode">
        <abstract>
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
<!-- ####################
  SECTION level-1 elements
     ####################-->
<xsl:template match="section[@level='1']">
  <xsl:choose>
    <xsl:when test="$rootElement='book'">
      <chapter>
        <title><xsl:apply-templates select="heading" /></title>
        <xsl:apply-templates select=".//textbox[ancestor::section[1] = current()]" mode="partextbox" />
        <xsl:choose>
          <xsl:when test="count( *[name() != 'heading'] ) > 0">
            <xsl:apply-templates select="*[name() != 'heading']" />
          </xsl:when>
          <xsl:otherwise>
            <!-- write empty paragraph to be valid -->
            <para></para>
          </xsl:otherwise>
        </xsl:choose>
      </chapter>
    </xsl:when>
    <xsl:when test="$rootElement='article'">
      <section>
		<xsl:if test="heading[@class='swsReferenceTitle']">
			<xsl:attribute name="role">
				<xsl:text>swsReferenceTitle</xsl:text>
			</xsl:attribute>
		</xsl:if>
        <title><xsl:apply-templates select="heading" /></title>
        <xsl:apply-templates select=".//textbox[ancestor::section[1] = current()]" mode="partextbox" />
        <xsl:choose>
          <xsl:when test="count( *[name() != 'heading'] ) > 0">
            <xsl:apply-templates select="*[name() != 'heading']" />
          </xsl:when>
          <xsl:otherwise>
            <!-- write empty paragraph to be valid -->
            <para></para>
          </xsl:otherwise>
        </xsl:choose>
      </section>
    </xsl:when>
  </xsl:choose>    
</xsl:template>

    <!-- ####################
         LINK element
         ####################-->
    <xsl:template match="link">
      <xsl:choose>
        <xsl:when test="starts-with(@xlink:href, '#')">
          <link>
             <xsl:attribute name="linkend"><xsl:value-of select="my-class:makeValidID(@rawtarget)" /></xsl:attribute>
             <xsl:apply-templates />
          </link>
        </xsl:when>
        <xsl:otherwise>
          <ulink>
             <xsl:attribute name="url"><xsl:value-of select="@xlink:href" /></xsl:attribute>
             <xsl:if test="substring(@xlink:href, string-length(@xlink:href) - string-length('/fullscreen') + 1) = '/fullscreen'">
               <xsl:attribute name="type">_blank</xsl:attribute>            
             </xsl:if>
             <xsl:apply-templates />
          </ulink>
        </xsl:otherwise>
      </xsl:choose>
    </xsl:template>

</xsl:stylesheet>
