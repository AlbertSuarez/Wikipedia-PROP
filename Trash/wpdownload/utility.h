#include <string>
 
namespace Utility {
	std::string urlEncode(const std::string &url);
	std::string urlDecode(const std::string &encoded);

	std::string charToHex(unsigned char c);
	unsigned char hexToChar(const std::string &str);

	std::string replaceChar(std::string &str, char ch1, char ch2);
}
