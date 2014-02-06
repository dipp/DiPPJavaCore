#!/usr/bin/perl 


my $infile = @ARGV[0];
my $destfolder = @ARGV[1];
my $outfile = @ARGV[2];

my $RegEx = qr"<\?xml version=\"1.0";


open (INFILE, "< $infile") or die "Can't open $file: $!";
open (OUTFILE, "> $destfolder$outfile") or die "Can't write $file: $!";

@lines = <INFILE>;

while($line = shift @lines) {
		# next if ( $line =~ m/\s*$RegEx\s*/o );
		next if ( $line =~ m/^\s*$/o );
        $line =~ s/\xc2\xad//go;
        $line =~ s/\xc2\xa0/ /go;
        $line =~ s/\s+/ /go;
		print OUTFILE $line;
	
}


close ( INFILE );
close ( OUTFILE );
