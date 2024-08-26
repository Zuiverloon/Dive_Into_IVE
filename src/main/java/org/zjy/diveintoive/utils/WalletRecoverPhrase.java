package org.zjy.diveintoive.utils;

public class WalletRecoverPhrase {

    public enum WalletType {
        METAMASK("metamask"),
        PHANTOM("phantom"),
        UNISAT("unisat"),
        ERA("era"),
        ECOCHAINLESS("ecochainless"),
        VENOM("venom"),
        BITGET("bitget");

        private String name;

        WalletType(String name) {
            this.name = name;
        }
    }

    public static String[] metaMaskToken = {
            "qHk0igszO94=",
            "p46AR84v1X4=",
            "+MpO1HXsNoM=",
            "zDcfARWOI4M=",
            "LKdnL2kmtsg=",
            "VL9szRwvahs=",
            "7RqAj9rsvck=",
            "ybtEELhPgoE=",
            "HR0gYtqmf5A=",
            "1RZA7lZosRc=",
            "v8fZNakESEM=",
            "TKXm+Pdjfjo="
    };

    public static String[] phantomToken = {
            "iCcJwBP+3Z4=",
            "XHR59ljrhck=",
            "3Q/mGhfCXPI=",
            "FID6kUgOOgA=",
            "D1kEt8cOZtI=",
            "Pcurn3wbKJw=",
            "7Yb/kC3Pkzg=",
            "WBgCG8dTpq4=",
            "0AYRoV5XOYY=",
            "pEklWm466Ys=",
            "qIVhA6dNH6I=",
            "MfPow9M240E="
    };

    public static String[] unisatToken = {
            "W5RW8tw3Qvw=",
            "o+Bko9tvFKg=",
            "tTKjqI4TVZI=",
            "d5bSuYV59YQ=",
            "fl+7/5scYY8=",
            "EvV80S9yJ6w=",
            "Nr70caGHSe8=",
            "fHmoRG+Svmo=",
            "0W3Z+OWRSwo=",
            "wacvkQeqtBQ=",
            "OASkcxhFriA=",
            "hM52tyjTyD4="
    };

    public static String[] eraToken = {
            "UNLoRGEQj7c=",
            "YDDbSD/EVbM=",
            "8tWInx4cd8s=",
            "CEWbSDrpZPA=",
            "NzCAtXG2dZ4=",
            "dxkumzh08Us=",
            "GGKZ2eBxJpA=",
            "uAtwF37rvw8=",
            "W5cowQQuOA8=",
            "ANsawJq2REM=",
            "cyi+qCViJww=",
            "zCh4siJLUE0="
    };

    public static String[] ecochainlessToken = {
            "tpq+uW+ffsw=",
            "XIPjiutr/U0=",
            "HEqWjhDP3NQ=",
            "eCtCHGc7pjw=",
            "BkIkZts3qJ0=",
            "Wr/nHLI5SpQ=",
            "2z/jFKvrC6I=",
            "aWl/WxX9h7A=",
            "sWuJ49QfqEc=",
            "23iS1KWEnmY=",
            "b4Y1K2tZUqA=",
            "EPrErVXVWx0="
    };

    public static String[] venomToken = {
            "ANaz3ZhuPuU=",
            "ikQdvD2R9is=",
            "z+CZ3NAAmt8=",
            "Jf29aU/mD2A=",
            "AnG11MC/5v8=",
            "gAzvbx6AObQ=",
            "QAnMpna9bcM=",
            "4xeT2SIDlA0=",
            "NBnEpQIyQuY=",
            "0KXC+MzQvRY=",
            "6/e1c84iIhg=",
            "uU1Q/Ew5hv8="
    };

    public static String[] bitgetToken = {
            "wKXXGPydqB0=",
            "XHR59ljrhck=",
            "2xdtdTwIJNo=",
            "xK/HfvQpZgI=",
            "cQHlYZKG6cg=",
            "XZUXelg8SM4=",
            "cmeZUwz+ahM=",
            "1zBEKJSXvWZIFI6A1HxPOg==",
            "8hYCm8O51m9IFI6A1HxPOg==",
            "opBgHTtQew4=",
            "GLWYda5u8ec=",
            "J7HykYoXCE8="
    };

    public static String[] fuelToken = {
            "Ovk/r6E+jsM=",
            "ytJZrjUvf+c=",
            "VmwWTnZcj34=",
            "HmRXamyW2ZA=",
            "XjphqzwIoJ4=",
            "SDcp8JI3+aU=",
            "CC+zJ9d2+JE=",
            "64XrOl8si2U=",
            "s7SP4KhOMyU=",
            "lWV+Ei+fxFs=",
            "MhnHcnuODWw=",
            "/8AqRVNiltY="
    };

    public static String[] subwallet = {
            "QQcH+xBXSQQ=",
            "UHsJp9xOfa0=",
            "adSjYSAEQLE=",
            "pLFD1qITSTM=",
            "0M5zJQeDHxc=",
            "NxHc5Bp4YYY=",
            "3DprEVDY/Mo=",
            "wxS4js/yGBA=",
            "O2XbDlw4euU=",
            "uSk/GGNuPqY=",
            "RMA7jUheyaxIFI6A1HxPOg==",
            "0AO4m2muHYk="
    };

    public static String[] tonWallet = {
            "ziKLMkVzFZZIFI6A1HxPOg==",
            "6gLSwtic+gE=",
            "l++pOJzfef0=",
            "9UxtQ9rnz9E=",
            "70T37vkyIJk=",
            "mt32/VD4LT4=",
            "LZeKWE0Jk00=",
            "uVyfQxoS4A8=",
            "BVP9uGJ0MaI=",
            "oJenJ0hpU8M=",
            "WsG+7ykaeJ4=",
            "7Nz1ideoImM=",
            "+bnKnF7YYXw=",
            "nLfrwXg+O0I=",
            "C9LvsMUhIl0=",
            "3+JHCBohxhs=",
            "SDaRPxTnSaU=",
            "Vtc98a7wWB8=",
            "zgJxdeLPcw4=",
            "MFri/GdMC7Q=",
            "dABkBGbi62k=",
            "LyYK4AbQuYo=",
            "I5T4mnXdSDw=",
            "xgDDgGBIOKE="
    };

    public static String[] razorWallet = {
            "DybjnWeceMk=",
            "2lBW4rL7Ut4=",
            "5bQ3RSf/yVM=",
            "1WW0g7NHnQw=",
            "OJs0oFIyBE4=",
            "QSvmdm22J5s=",
            "70vGrv6iPG8=",
            "zX41ewOL3mI=",
            "j5LaehLcfwk=",
            "ANsawJq2REM=",
            "1xGwnnAFDxE=",
            "1fIfztyfuNw="
    };

    public static String[] memefiWallet = {
            "yplLkG3nV1E=",
            "TYxFr2+sHds=",
            "yz3C70GjaS0=",
            "y/1LB3u1xvE=",
            "L2jf8S7uEMI=",
            "AXeMTQks3ww=",
            "0W3Z+OWRSwo=",
            "C89zS/zDbzs=",
            "JeXkwmdhsBw=",
            "RfGdV8Nhpy0=",
            "c3rCu0JK+Pc=",
            "8mhEf1ZhazA="
    };


}
