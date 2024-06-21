{
  inputs.nixpkgs.url = "github:nixos/nixpkgs/nixos-24.05";

  outputs =
    { self, nixpkgs, ... }:
    let
      system = "x86_64-linux";
      pkgs = nixpkgs.legacyPackages.${system};
    in
    {
      devShells.${system}.default = pkgs.mkShell {
        packages = [ pkgs.jdk ];
        JAVA_HOME = pkgs.jdk.home;
      };

      packages.${system}.default = pkgs.stdenv.mkDerivation {
        name = "battleship-over-lan";
        src = self;

        nativeBuildInputs = [
          pkgs.jdk
          pkgs.makeWrapper
        ];

        buildPhase = ''
          cd src
          javac de/lgsit/battleshipoverlan/*.java
          jar -cf $out/share/java/battleship-over-lan.jar de/lgsit/battleshipoverlan/*.class
        '';

        installPhase = ''
          mkdir -p $out/bin
          makeWrapper ${pkgs.jre}/bin/java $out/bin/battleship-over-lan \
            --add-flags "-cp $out/share/java/battleship-over-lan.jar de.lgsit.battleshipoverlan.Main"
        '';
      };

      formatter.${system} = pkgs.nixfmt-rfc-style;
    };
}
