{
  inputs.nixpkgs.url = "github:nixos/nixpkgs/nixos-24.05";

  outputs =
    { nixpkgs, ... }:
    let
      system = "x86_64-linux";
      pkgs = nixpkgs.legacyPackages.${system};
    in
    {
      devShells.${system}.default = pkgs.mkShell { 
        packages = [ pkgs.jdk ];
        JAVA_HOME = pkgs.jdk.home;
      };
    };
}
